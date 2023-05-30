package com.cloud.jack.app.excel;

import com.alibaba.excel.support.ExcelTypeEnum;

public class DownloadUtil {

    public static ExcelTypeEnum getExcelType(String fileName){
        if(fileName.endsWith(".xls")){
            return ExcelTypeEnum.XLS;
        } else if (fileName.endsWith(ExcelTypeEnum.XLSX.getValue())) {
            return ExcelTypeEnum.XLSX;
//        }else if(fileName.endsWith(ExcelTypeEnum.CSV.getValue())){
//            return ExcelTypeEnum.CSV;
        }
        return null;
    }

}
