package com.cloud.jack.gen.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Columns {

    @ApiModelProperty(value = "字段名")
    private String columnName;

    @ApiModelProperty(value = "是否非空")
    private Integer required;

    @ApiModelProperty(value = "是否主键")
    private Integer pk;

    @ApiModelProperty(value = "注释")
    private String columnComment;

    @ApiModelProperty(value = "是否自增")
    private Integer incremented;

    @ApiModelProperty(value = "类型")
    private String columnType;
}
