package com.cloud.jack.app.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.cloud.jack.app.entity.AmzOrderAll;

import java.util.List;
import java.util.Map;

public class MyPageReadListener extends EasyExcelListener<AmzOrderAll> {


    public MyPageReadListener(List list) {
        super(list);
    }

}
