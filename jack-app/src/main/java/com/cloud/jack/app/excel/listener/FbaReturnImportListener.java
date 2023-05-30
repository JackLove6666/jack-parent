package com.cloud.jack.app.excel.listener;

import cn.hutool.core.util.StrUtil;
import com.cloud.jack.app.entity.vo.fbaReturn.WmsFbaReturnImportCheckVo;
import com.cloud.jack.app.entity.vo.fbaReturn.WmsFbaReturnImportExcelVo;
import com.cloud.jack.app.excel.EasyExcelListener;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FbaReturnImportListener extends EasyExcelListener<WmsFbaReturnImportExcelVo> {

    private WmsFbaReturnImportCheckVo wmsFbaReturnImportCheckVo;

    private List<String> errorMsg;
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     *
     * @param list
     */
    public FbaReturnImportListener(List<WmsFbaReturnImportExcelVo> list,WmsFbaReturnImportCheckVo wmsFbaReturnImportCheckVo,List<String> errorMsg) {
        super(list);
        this.wmsFbaReturnImportCheckVo = wmsFbaReturnImportCheckVo;
        this.errorMsg = errorMsg;
    }

    @Override
    public void checkOwnData(WmsFbaReturnImportExcelVo data, int currentRowNum) {
        //最多保存30条错误信息
        if(errorMsg.size()>=30){
            return;
        }

        StringBuffer sb = new StringBuffer();

        String returnPlatformType = data.getReturnPlatformType();
        if(StrUtil.isBlank(returnPlatformType)){
            sb.append("退货平台不能为空;");
        }
        String returnPlatformSite = data.getReturnPlatformSite();
        if(StrUtil.isBlank(returnPlatformSite)){
            sb.append("退货站点不能为空;");
        }
        Map<Pair<String, String>, Integer> bsePlatformSitMap = wmsFbaReturnImportCheckVo.getBsePlatformSitMap();
        if(StrUtil.isNotBlank(returnPlatformType) && StrUtil.isNotBlank(returnPlatformSite)){
            if(!bsePlatformSitMap.containsKey(Pair.of(returnPlatformType,returnPlatformSite))){
                sb.append(String.format("退货平台+退货站点：[%s,%s]找不到对应关系;",returnPlatformType,returnPlatformSite));
            }else{
                data.setReturnPlatformSiteId(bsePlatformSitMap.get(Pair.of(returnPlatformType,returnPlatformSite)));
            }
        }
        String returnStore = data.getReturnStore();
        Map<Pair<Integer, String>, Integer> storeMap = wmsFbaReturnImportCheckVo.getStoreMap();
        if(StrUtil.isBlank(returnStore)){
            sb.append("退货店铺不能为空;");
        }else if(data.getReturnPlatformSiteId() != null){
            if(!storeMap.containsKey(Pair.of(data.getReturnPlatformSiteId(),returnStore))){
                sb.append(String.format("退货平台+退货站点+店铺：[%s,%s,%s]找不到对应关系;",returnPlatformType,returnPlatformSite,returnStore));
            }else{
                data.setReturnStoreId(storeMap.get(Pair.of(data.getReturnPlatformSiteId(),returnStore)));
            }
        }

        Map<String, Integer> platformSkuMap = wmsFbaReturnImportCheckVo.getPlatformSkuMap();
        String bsePlatformSkuCode = data.getBsePlatformSkuCode();
        if(StrUtil.isBlank(bsePlatformSkuCode)) {
            sb.append("平台sku不能为空;");
        } else if (data.getReturnPlatformSiteId() != null && data.getReturnStoreId() != null) {
            String platformMapKey = String.format("%s-%s-%s", bsePlatformSkuCode, data.getReturnStoreId(), data.getReturnPlatformSiteId());
            if(!platformSkuMap.containsKey(platformMapKey)){
                sb.append(String.format("退货平台+退货站点+店铺+平台sku：[%s,%s,%s,%s]找不到对应关系;",returnPlatformType,returnPlatformSite,returnStore,bsePlatformSkuCode));
            }else{
                data.setBsePlatformSkuId(platformSkuMap.get(platformMapKey));
            }
        }

        List<String> saleStatuList = Arrays.asList("是", "否");
        String saleStatus = data.getSaleStatus();
        if(StrUtil.isBlank(saleStatus)) {
            sb.append("是否可售不能为空;");
        }else if (!saleStatuList.contains(saleStatus)) {
            sb.append("是否可售只能是是或否;");
        }else {
            data.setSaleStatus(saleStatus.equals("是") ? "1" : "0");
        }
        String returnQuantity = data.getReturnQuantity();
        if(StrUtil.isBlank(returnQuantity)) {
            sb.append("退货数量不能为空;");
        }else {
            if(!StrUtil.isNumeric(returnQuantity)){
                sb.append("退货数量只能是数字;");
            }else {
                if(Integer.parseInt(returnQuantity) <= 0){
                    sb.append("退货数量必须大于0;");
                }
            }

        }
        String remark = data.getRemark();
        if(StrUtil.isNotBlank(remark) && remark.length() > 200){
            sb.append("备注长度不能超过200;");
        }
        if(sb.length() > 0){
            errorMsg.add(String.format("第%s行有如下错误：%s",currentRowNum,sb.toString()));
        }
    }
}
