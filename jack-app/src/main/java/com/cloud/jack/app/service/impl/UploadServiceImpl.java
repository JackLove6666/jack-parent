package com.cloud.jack.app.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.jack.app.config.CommonProperties;
import com.cloud.jack.app.core.R;
import com.cloud.jack.app.core.TrobDeniedException;
import com.cloud.jack.app.entity.AttachEntity;
import com.cloud.jack.app.entity.SourceBillType;
import com.cloud.jack.app.entity.SourceMq;
import com.cloud.jack.app.entity.SourceUploadReportManage;
import com.cloud.jack.app.enums.UploadStatusEnum;
import com.cloud.jack.app.rabbit.RabbitSender;
import com.cloud.jack.app.service.*;
import com.cloud.jack.app.utils.FileUtil;
import com.cloud.jack.app.utils.RedissonLocker;
import com.cloud.jack.app.utils.RegexUtils;
import com.cloud.jack.app.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
