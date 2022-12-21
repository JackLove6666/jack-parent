package com.cloud.jack.gen.entity;


import com.cloud.jack.core.utils.CamelCaseUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Tables {

    @ApiModelProperty(value = "数据库名")
    private String tableCat;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "表注释")
    private String remarks;

    @ApiModelProperty(value = "Java类名称")
    private String className;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public String getClassName() {
        return CamelCaseUtil.toCapitalizeCamelCase(getTableName());
    }
}
