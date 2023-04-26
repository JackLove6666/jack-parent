package com.cloud.jack.app.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
public class EasyExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> list;

    public EasyExcelListener(List list) {
        this.list = list;
    }

    public void invoke(Object data, AnalysisContext context) {
        if(data!=null) {
            int currentRowNum = context.getCurrentRowNum();
            list.add((T) data);
            checkOwnData((T) data,currentRowNum);
        }
    }

    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public void checkOwnData(T data, int currentRowNum) {

    }


}
