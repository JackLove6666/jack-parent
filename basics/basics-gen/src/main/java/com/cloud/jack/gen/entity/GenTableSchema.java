package com.cloud.jack.gen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.jack.core.CoreModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 代码生成规范-表结构
 *
 * @author Leo
 * @date 2021-06-13
 */
@Data
@TableName("gen_table_schema")
@ApiModel(value = "代码生成规范-表结构")
public class GenTableSchema extends CoreModel<GenTableSchema> {

    @ApiModelProperty(value = "数据库名")
    private String tableCat;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "表注释")
    private String remarks;

    @ApiModelProperty(value = "类名称")
    private String className;

    @ApiModelProperty(value = "包名称")
    private String packages;

    @ApiModelProperty(value = "模块")
    private String module;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    @TableField(exist = false)
    @ApiModelProperty(value = "行结构")
    private List<GenColumnSchema> columns;
}
