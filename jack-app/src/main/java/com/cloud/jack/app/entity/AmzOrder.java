/*
 *    Copyright (c) 2018-2025, Aukey All rights reserved.
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
 * Author: wangbin (ApemansIT@apemans.com)
 */
package com.cloud.jack.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 亚马逊订单报告
 *
 * @author 捷克梗
 * @date 2022-08-10 14:48:02
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("amz_order")
@ApiModel(value="亚马逊订单报告")
public class AmzOrder extends Model<AmzOrder> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    @TableId
    private Long id;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String orderId;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String orderItemId;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private LocalDateTime purchaseDate;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private LocalDateTime paymentsDate;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String buyerEmail;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String buyerName;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String buyerPhoneNumber;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String sku;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String productName;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private Integer quantityPurchased;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String currency;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private BigDecimal itemPrice;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private BigDecimal itemTax;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private BigDecimal shippingPrice;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private BigDecimal shippingTax;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipServiceLevel;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String recipientName;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipAddress1;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipAddress2;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipAddress3;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipCity;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipState;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipPostalCode;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipCountry;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String shipPhoneNumber;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private LocalDateTime deliveryStartDate;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private LocalDateTime deliveryEndDate;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private LocalDateTime deliveryTimeZone;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String deliveryInstructions;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String isBusinessOrder;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String purchaseOrderNumber;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String priceDesignation;

    @ApiModelProperty(value="店铺ID",example="")
    private Integer storeId;
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
  
}
