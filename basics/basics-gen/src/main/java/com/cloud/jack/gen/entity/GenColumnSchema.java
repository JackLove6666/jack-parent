package com.cloud.jack.gen.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.cloud.jack.core.CoreModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 代码生成规范-行结构
 *
 * @author Leo
 * @date 2021-06-13
 */
@Data
@TableName("gen_column_schema")
@ApiModel(value = "代码生成规范-行结构")
public class GenColumnSchema extends CoreModel<GenColumnSchema> {

    @ApiModelProperty(value = "父类ID")
    private String parentId;

    @ApiModelProperty(value = "数据库名")
    private String tableCat;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "行名称")
    private String columnName;

    @ApiModelProperty(value = "行类型")
    private String typeName;

    @ApiModelProperty(value = "允许空")
    private Boolean nullable;

    @ApiModelProperty(value = "表注释")
    private String remarks;

    @ApiModelProperty(value = "成员变量")
    private String varName;

    @ApiModelProperty(value = "是否主键")
    private Boolean primaryKey;

    @ApiModelProperty(value = "是否查询：0-否，1-是")
    private Boolean isSearch;

    @ApiModelProperty(value = "导入导出：0-否，1-导入，2-导出，3-导入导出")
    private Integer isExcel;

    @ApiModelProperty(value = "是否列表：0-否，1-是")
    private Boolean isList;

    @ApiModelProperty(value = "是否编辑：0-否，1-是")
    private Boolean isEdit;

}
