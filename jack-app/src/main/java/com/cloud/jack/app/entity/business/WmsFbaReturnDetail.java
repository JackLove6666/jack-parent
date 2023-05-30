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
 * fba退货明细表
 *
 * @author 捷克梗
 * @date 2023-05-09 16:15:19
 */
@Data
@TableName("wms_fba_return_detail")
@ApiModel(value="fba退货明细表")
public class WmsFbaReturnDetail extends Model<WmsFbaReturnDetail> {
private static final long serialVersionUID = 1L;

    /**
   * fba退货明细表id
   */
    @ApiModelProperty(value="fba退货明细表id",example="")
    @TableId
    private Integer id;
    /**
   * 主表单号
   */
    @ApiModelProperty(value="主表单号",example="")
    private String returnOrderNo;
    /**
   * 平台SKU_ID
   */
    @ApiModelProperty(value="平台SKU_ID",example="")
    private Integer bsePlatformSkuId;
    /**
   * 退货数量
   */
    @ApiModelProperty(value="退货数量",example="")
    private Integer returnQuantity;
    /**
   * 到货数量
   */
    @ApiModelProperty(value="到货数量",example="")
    private Integer arriveQuantity;
    /**
   * 销售状态0在售，1停售
   */
    @ApiModelProperty(value="销售状态0在售，1停售",example="")
    private Integer saleStatus;
    /**
   * 物流追踪号
   */
    @ApiModelProperty(value="物流追踪号",example="")
    private String logisticsTrackNo;
    /**
   * 是否删除 1：已删除  0：正常
   */
    @ApiModelProperty(value="是否删除 1：已删除  0：正常",example="")
    private String delFlag;
    /**
   * 可售数量
   */
    @ApiModelProperty(value="可售数量",example="")
    private Integer saleQuantity;
  
}
