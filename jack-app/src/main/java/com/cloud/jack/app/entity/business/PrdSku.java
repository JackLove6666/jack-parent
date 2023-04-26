/*
 *    Copyright (c) 2018-2035, Apemans All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the trob4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: ApemansIT (itsupport@apemans.com)
 */
package com.cloud.jack.app.entity.business;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * SKU主表
 *
 * @author trobs code generator
 * @date 2023-04-26 15:42:45
 */
@Data
@TableName("prd_sku")
@ApiModel(value="SKU主表")
public class PrdSku extends Model<PrdSku> {
private static final long serialVersionUID = 1L;

    /**
   * SKU_ID
   */
    @ApiModelProperty(value="SKU_ID",example="")
    @TableId
    private Integer skuId;
    /**
   * SKU代码
   */
    @ApiModelProperty(value="SKU代码",example="")
    private String skuCode;
    /**
   * SKU名称
   */
    @ApiModelProperty(value="SKU名称",example="")
    private String skuName;
    /**
   * 产品属性
   */
    @ApiModelProperty(value="产品属性",example="")
    private String skuAttributes;
    /**
   * 采购周期
   */
    @ApiModelProperty(value="采购周期",example="")
    private Integer purchaseCycle;
    /**
   * 规格参数
   */
    @ApiModelProperty(value="规格参数",example="")
    private String specification;
    /**
   * 功能需求
   */
    @ApiModelProperty(value="功能需求",example="")
    private String funcRequire;
    /**
   * 认证需求
   */
    @ApiModelProperty(value="认证需求",example="")
    private String approveRequire;
    /**
   * 包装清单
   */
    @ApiModelProperty(value="包装清单",example="")
    private String packList;
    /**
   * 其他描述
   */
    @ApiModelProperty(value="其他描述",example="")
    private String otherDescribe;
    /**
   * SPU ID
   */
    @ApiModelProperty(value="SPU ID",example="")
    private Integer spuId;
    /**
   * 单据ID
   */
    @ApiModelProperty(value="单据ID",example="")
    private Integer invoiceId;
    /**
   * 平台类型ID
   */
    @ApiModelProperty(value="平台类型ID",example="")
    private Integer platformTypeId;
    /**
   * 处理环节 0 未处理 1开发审核 2销售审核 3 项目办审核 4 财务审核 5审核完成
   */
    @ApiModelProperty(value="处理环节 0 未处理 1开发审核 2销售审核 3 项目办审核 4 财务审核 5审核完成",example="")
    private Integer status;
    /**
   * 版本
   */
    @ApiModelProperty(value="版本",example="")
    private String version;
    /**
   * 创建人
   */
    @ApiModelProperty(value="创建人",example="")
    private Integer createBy;
    /**
   * 创建时间
   */
    @ApiModelProperty(value="创建时间",example="")
    private LocalDateTime createTime;
    /**
   * 最后更新人
   */
    @ApiModelProperty(value="最后更新人",example="")
    private Integer updateBy;
    /**
   * 最后更新时间
   */
    @ApiModelProperty(value="最后更新时间",example="")
    private LocalDateTime updateTime;
    /**
   * 是否删除 1：已删除  0：正常
   */
    @ApiModelProperty(value="是否删除 1：已删除  0：正常",example="")
    private String delFlag;
    /**
   * 开发时间
   */
    @ApiModelProperty(value="开发时间",example="")
    private LocalDateTime finishAuditTime;
  
}
