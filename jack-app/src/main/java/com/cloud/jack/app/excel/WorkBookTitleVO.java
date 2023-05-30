package com.cloud.jack.app.excel;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkBookTitleVO implements Serializable {

    /**
     * sheet页数
     */
    private Integer sheetPage;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列名所对应的表格列数
     */
    private Integer cellIndex;

}
