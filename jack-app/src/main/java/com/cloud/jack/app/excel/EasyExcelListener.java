package com.cloud.jack.app.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
public class EasyExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> list;

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    public EasyExcelListener(List list) {
        this.list = list;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Object data, AnalysisContext context) {
        if(data!=null) {
            int currentRowNum = context.getCurrentRowNum();
            list.add((T) data);
            checkOwnData((T) data,currentRowNum);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public void checkOwnData(T data, int currentRowNum) {

    }


}
