package com.cloud.jack.app.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.cloud.jack.app.config.CommonProperties;
import com.cloud.jack.app.core.R;
import com.cloud.jack.app.core.TrobDeniedException;
import com.cloud.jack.app.entity.*;
import com.cloud.jack.app.entity.business.WmsFbaReturn;
import com.cloud.jack.app.entity.business.WmsFbaReturnDetail;
import com.cloud.jack.app.entity.vo.fbaReturn.WmsFbaReturnImportCheckVo;
import com.cloud.jack.app.entity.vo.fbaReturn.WmsFbaReturnImportExcelVo;
import com.cloud.jack.app.enums.UploadStatusEnum;
import com.cloud.jack.app.excel.listener.FbaReturnImportListener;
import com.cloud.jack.app.mapper.DemoOrderImportCheckMapper;
import com.cloud.jack.app.mapper.WmsFbaReturnDetailMapper;
import com.cloud.jack.app.mapper.WmsFbaReturnMapper;
import com.cloud.jack.app.rabbit.RabbitSender;
import com.cloud.jack.app.service.*;
import com.cloud.jack.app.utils.CreateNoUtils;
import com.cloud.jack.app.utils.FileUtil;
import com.cloud.jack.app.utils.RedissonLocker;
import com.cloud.jack.app.utils.ThreadPoolUtil;
import com.cloud.jack.app.excel.ParserHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private SourceBillTypeService sourceBillTypeService;

    @Autowired
    private SourceUploadReportManageService sourceUploadReportManageService;

    @Autowired
    private RabbitSender rabbitSender;

    @Autowired
    private RedissonLocker redissonLocker;

    @Autowired
    private SourceMqService sourceMqService;

    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private SysAttachTService sysAttachTService;

    @Autowired
    private AmzOrderAllService amzOrderAllService;

    @Autowired
    private DemoOrderImportCheckMapper demoOrderImportCheckMapper;

    @Autowired
    private WmsFbaReturnMapper wmsFbaReturnMapper;

    @Autowired
    private WmsFbaReturnDetailMapper wmsFbaReturnDetailMapper;

    @Override
    public R batchImport(List<MultipartFile> files) {
        if(CollUtil.isEmpty(files)){
            throw  new RuntimeException("未上传任何文件");
        }
        //检查文件重复
        //检查文件后缀
        //检查文件内容是否重复 --MD5
        List<SourceBillType> sourceBillTypeList = sourceBillTypeService.list();
        if(CollUtil.isEmpty(sourceBillTypeList)){
            throw new TrobDeniedException("未维护任何可以导入的报表类型");
        }
        Map<MultipartFile, SourceBillType> fileMap = new HashMap<>();

        try {

//            redissonLocker.tryDoBusiness("key",10,15,()->{
                files.forEach(file -> {
                    String originalFilename = file.getOriginalFilename();
                    for (SourceBillType sourceBillType : sourceBillTypeList) {
//                        Boolean matchData = RegexUtils.matchData(sourceBillType.getNameRegex(), originalFilename);
//                        if(matchData){
                            fileMap.put(file,sourceBillType);
//                        }
                    }
                });

                List<AttachEntity> attachEntityList = new ArrayList<>();
                Map<MultipartFile, String> filePathMap = new HashMap<>();
                for (MultipartFile file : files) {
                    AttachEntity attachEntity = saveFile(file);
                    filePathMap.put(file, attachEntity.getFilePath());
                    attachEntityList.add(attachEntity);
                }
                sysAttachTService.saveBatch(attachEntityList);


//        R<Map<MultipartFile, String>> uploadFiles = FileServerUtil.uploadFiles(files.toArray(new MultipartFile[files.size()]), CommonConstant.UPLOAD_PATH);
//        if(uploadFiles.getCode() != CommonConstant.SUCCESS){
//            throw new TrobDeniedException("文件上传失败！");
//        }
//        Map<MultipartFile, String> filePathMap = uploadFiles.getData();
                List<SourceUploadReportManage> sourceUploadReportManageList = new ArrayList<>();
                fileMap.forEach(((file, sourceBillType) -> {

                    String uuid = UUID.randomUUID().toString();
                    String billType = sourceBillType.getBillType();
                    String filePath = filePathMap.get(file);
                    String originalFilename = file.getOriginalFilename();

                    SourceUploadReportManage sourceUploadReportManage = new SourceUploadReportManage();
                    sourceUploadReportManage.setBatchNo(uuid);
                    sourceUploadReportManage.setBillType(billType);
                    Map<String, Object> sendMqMap = sourceUploadReportManage.getSendMqMap();
                    sendMqMap.put("billType",billType);
                    sendMqMap.put("filePath",filePath);
                    sendMqMap.put("fileName",originalFilename);
                    sendMqMap.put("batchNo",uuid);
                    sendMqMap.put("userId",666);
                    sourceUploadReportManage.setFileName(originalFilename);
                    sourceUploadReportManage.setFilePath(filePath);
                    sourceUploadReportManage.setSendMqMap(sendMqMap);
                    sourceUploadReportManage.setStatus(UploadStatusEnum.PARSING.getKey());
                    sourceUploadReportManage.setCreateBy(666);
                    sourceUploadReportManage.setUpdateBy(666);
                    sourceUploadReportManage.setUploadBy(666);
                    sourceUploadReportManage.setUploadTime(new Date());
                    sourceUploadReportManage.setFile(file);
                    sourceUploadReportManageList.add(sourceUploadReportManage);
                }));
                sourceUploadReportManageService.saveBatch(sourceUploadReportManageList);
                Map<Long, SourceMq> sourceMqMap = sourceMqService.list().stream().collect(Collectors.toMap(key -> key.getSourceMqId(), Function.identity(), (key1, key2) -> key1));
                //多线程处理
                ThreadPoolExecutor namedExecutor = ThreadPoolUtil.createNamedExecutor("上传报告", 50);
                sourceUploadReportManageList.forEach(item ->{
                    Callable<SourceUploadReportManage> callable = () -> {
                        item.getSendMqMap().put("id",item.getSourceUploadReportManageId());
                        SourceMq sourceMq = sourceMqMap.get(fileMap.get(item.getFile()).getSourceMqId());
                        String messageKey = String.format("%s-%s", item.getBillType(), item.getSourceUploadReportManageId());
                        rabbitSender.convertAndSend(sourceMq.getExchangeName(),sourceMq.getBindKey(), JSON.toJSONString(item.getSendMqMap()),messageKey);
                        return item;
                    };
                    Future<SourceUploadReportManage> submit = namedExecutor.submit(callable);
                    try {
                        log.info("发送的消息："+submit.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
//            });
        }catch (Exception e){
            log.error("获取锁失败！",e);
        }
        return R.ok("上传成功");



    }


    AttachEntity saveFile(MultipartFile file){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        AttachEntity attachEntity = new AttachEntity();
        String originalFilename = file.getOriginalFilename();
        //附件唯一标识
        attachEntity.setFileId(uuid);
        //附件大小
        attachEntity.setFileSize(FileUtil.getFileSize(file.getSize()));
        //附件原始名称
        attachEntity.setName(originalFilename);
        //附件类型
        attachEntity.setFileType(file.getContentType());
        //存储地址
        String realPath = commonProperties.getHostPath();
        //判断远程地盘文件是否存在，如不存在就需要创建
        File folder = FileUtil.mkdir(realPath);
        //获取文件新名称， uuid + 后缀
        String newName = uuid + originalFilename.substring(originalFilename.lastIndexOf("."));
        attachEntity.setFilePath(folder.getPath().replace(commonProperties.getHostPath(),"")+"\\"+newName);
        //判断目录是否存在，不存在则创建
        if(!folder.exists()){
            folder.mkdirs();
        }

        try {
            //保存文件
            file.transferTo(new File(folder,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return attachEntity;
    }

    /**
     * 导入 txt文件
     * @param file
     * @return
     */
    @Override
    @Transactional
    public R importExcel(MultipartFile file) {
        if(file == null){
            throw new RuntimeException("未上传任何文件");
        }
        try {
            List<JSONObject> jsonObjects = null;
            String originalFilename = file.getOriginalFilename();
            if(originalFilename.endsWith(".txt")){
                 jsonObjects = ParserHelper.parseAndCheckByTxt(file.getInputStream());
            } else if (originalFilename.endsWith(".xls") || originalFilename.endsWith(".xlsx")){
               jsonObjects = new ArrayList<>();
            }else if(originalFilename.endsWith(".csv")){
                jsonObjects = ParserHelper.parseAndCheckByCsv(file.getInputStream(), AmzOrderAll.class);
            }
            List<AmzOrderAll> amzOrderAllList = JSONObject.parseArray(JSON.toJSONString(jsonObjects), AmzOrderAll.class);
            amzOrderAllList.stream().forEach(item -> {
              item.setStoreId(1);
            });
            amzOrderAllService.saveBatch(amzOrderAllList);
            Iterator<AmzOrderAll> iterator = amzOrderAllList.iterator();
            while (iterator.hasNext()){
                AmzOrderAll next = iterator.next();
                log.info("{}",next);
            }
        } catch (IOException e) {
            log.error("导入失败[{}]",e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public R importExcel2(MultipartFile file) {
        if(file == null){
            throw new RuntimeException("未上传任何文件");
        }

        //平台站点
        List<Map<String, Object>> platformSitInfo = demoOrderImportCheckMapper.selectPlatformSitInfo();
        Map<Pair<String, String>, Integer> bsePlatformSitMap = platformSitInfo.stream().collect(Collectors.toMap(key -> Pair.of(key.get("platform_type_code").toString(), key.get("sit_name").toString()), value -> Integer.parseInt(value.get("bse_platform_sit_id").toString()), (key1, key2) -> key1));
        //店铺
        List<Map<String, Object>> allStoreInfo = demoOrderImportCheckMapper.selectAllStore();
        Map<Pair<Integer,String>, Integer> storeMap = allStoreInfo.stream().collect(Collectors.toMap(key -> Pair.of(Integer.parseInt(key.get("bse_platform_sit_id").toString()),key.get("store_account").toString()), value -> Integer.parseInt(value.get("store_id").toString()), (key1, key2) -> key1));
        //平台sku
        List<Map<String, Object>> allPlatFormSkuInfo = demoOrderImportCheckMapper.selectAllPlatFormSkuInfo();
        Map<String, Integer> platformSkuMap = allPlatFormSkuInfo.stream().collect(Collectors.toMap(key -> String.format("%s-%s-%s", key.get("platform_sku_code"), key.get("store_id"), key.get("bse_platform_sit_id")),
                value -> Integer.parseInt(value.get("bse_platform_sku_id").toString()), (key1, key2) -> key1));
        // 读取excel数据并检查数据
        List<WmsFbaReturnImportExcelVo> list = new ArrayList();
        List<String> errorMsg = new ArrayList<>();
        WmsFbaReturnImportCheckVo wmsFbaReturnImportCheckVo = new WmsFbaReturnImportCheckVo();
        wmsFbaReturnImportCheckVo.setBsePlatformSitMap(bsePlatformSitMap);
        wmsFbaReturnImportCheckVo.setStoreMap(storeMap);
        wmsFbaReturnImportCheckVo.setPlatformSkuMap(platformSkuMap);
        try {
            EasyExcel.read(file.getInputStream(), WmsFbaReturnImportExcelVo.class, new FbaReturnImportListener(list,wmsFbaReturnImportCheckVo,errorMsg)).sheet().doRead();
        } catch (IOException e) {
            log.error("导入失败[{}]",e);
            throw new RuntimeException(e);
        }
        if(CollectionUtils.isNotEmpty(errorMsg)){
            return R.fail(errorMsg.toString());
        }
        // 搜集插入数据
        List<WmsFbaReturn> wmsFbaReturnList = new ArrayList<>();
        List<WmsFbaReturnDetail> wmsFbaReturnDetailList = new ArrayList<>();
        for (WmsFbaReturnImportExcelVo wmsFbaReturnImportExcelVo : list) {
            WmsFbaReturn wmsFbaReturn = new WmsFbaReturn();
            wmsFbaReturn.setReturnOrderNo(getReturnFbaOrderNo());
            wmsFbaReturn.setSaleStatus(Integer.parseInt(wmsFbaReturnImportExcelVo.getSaleStatus()));
            wmsFbaReturn.setRemark(wmsFbaReturnImportExcelVo.getRemark());
            wmsFbaReturnList.add(wmsFbaReturn);
            WmsFbaReturnDetail wmsFbaReturnDetail = new WmsFbaReturnDetail();
            wmsFbaReturnDetail.setBsePlatformSkuId(wmsFbaReturnImportExcelVo.getBsePlatformSkuId());
            wmsFbaReturnDetail.setReturnQuantity(Integer.parseInt(wmsFbaReturnImportExcelVo.getReturnQuantity()));
            wmsFbaReturnDetail.setReturnOrderNo(wmsFbaReturn.getReturnOrderNo());
            wmsFbaReturnDetailList.add(wmsFbaReturnDetail);
        }
        // 插入数据
        for (WmsFbaReturn wmsFbaReturn : wmsFbaReturnList) {
            wmsFbaReturnMapper.insert(wmsFbaReturn);
        }
        for (WmsFbaReturnDetail wmsFbaReturnDetail : wmsFbaReturnDetailList) {
            wmsFbaReturnDetailMapper.insert(wmsFbaReturnDetail);
        }
        return R.ok("导入成功");
    }


        /**
         * 获取退货单号
         *
         * @return
         */
        private String getReturnFbaOrderNo() {
//            return createNoUtils.createNo(() -> baseMapper.selectMaxNo(), "FBA_RETURN_APPLY_NO", "FT");
            return CreateNoUtils.createNo("FT",wmsFbaReturnMapper.selectMaxNo());
        }

}
