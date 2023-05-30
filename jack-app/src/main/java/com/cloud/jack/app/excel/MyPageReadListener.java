package com.cloud.jack.app.excel;

import com.cloud.jack.app.entity.AmzOrderAll;

import java.util.List;

public class MyPageReadListener extends EasyExcelListener<AmzOrderAll> {


    public MyPageReadListener(List list) {
        super(list);
    }

}
