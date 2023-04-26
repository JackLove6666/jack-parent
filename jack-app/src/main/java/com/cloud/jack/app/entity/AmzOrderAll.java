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

import cn.hutool.core.annotation.Alias;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 亚马逊所有订单报告
 *
 * @author 捷克梗
 * @date 2022-08-10 14:47:54
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("amz_order_all")
@ApiModel(value="亚马逊所有订单报告")
public class AmzOrderAll extends Model<AmzOrderAll> {
private static final long serialVersionUID = 1L;


    @ApiModelProperty(value="",example="")
    @TableId
    @JsonIgnore
    private Long id;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "amazon-order-id")
    @Alias("amazon-order-id")
    private String amazonOrderId;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "merchant-order-id")
    @Alias("merchant-order-id")
    private String merchantOrderId;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "purchase-date")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd'T'HH:mm:ssXXX",timezone ="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @Alias("purchase-date")
    private LocalDateTime purchaseDate;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "last-updated-date")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd'T'HH:mm:ssXXX",timezone ="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @Alias("last-updated-date")
    private LocalDateTime lastUpdatedDate;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "order-status")
    @Alias("order-status")
    private String orderStatus;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "fulfillment-channel")
    @Alias("fulfillment-channel")
    private String fulfillmentChannel;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "sales-channel")
    @Alias("sales-channel")
    private String salesChannel;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "order-channel")
    @Alias("order-channel")
    private String orderChannel;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "url")
    @Alias("url")
    private String url;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "ship-service-level")
    @Alias("ship-service-level")
    private String shipServiceLevel;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "product-name")
    @Alias("product-name")
    private String productName;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "sku")
    @Alias("sku")
    private String sku;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "asin")
    @Alias("asin")
    private String asin;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "item-status")
    @Alias("item-status")
    private String itemStatus;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "quantity")
    @Alias("quantity")
    private Integer quantity;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "currency")
    @Alias("currency")
    private String currency;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "item-price")
    @Alias("item-price")
    private BigDecimal itemPrice;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "item-tax")
    @Alias("item-tax")
    private BigDecimal itemTax;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "shipping-price")
    @Alias("shipping-price")
    private BigDecimal shippingPrice;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "shipping-tax")
    @Alias("shipping-tax")
    private BigDecimal shippingTax;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "gift-wrap-price")
    @Alias("gift-wrap-price")
    private BigDecimal giftWrapPrice;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "gift-wrap-tax")
    @Alias("gift-wrap-tax")
    private BigDecimal giftWrapTax;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "item-promotion-discount")
    @Alias("item-promotion-discount")
    private BigDecimal itemPromotionDiscount;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "ship-promotion-discount")
    @Alias("ship-promotion-discount")
    private BigDecimal shipPromotionDiscount;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "ship-city")
    @Alias("ship-city")
    private String shipCity;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "ship-state")
    @Alias("ship-state")
    private String shipState;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "ship-postal-code")
    @Alias("ship-postal-code")
    private String shipPostalCode;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "ship-country")
    @Alias("ship-country")
    private String shipCountry;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "promotion-ids")
    @Alias("promotion-ids")
    private String promotionIds;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "is-business-order")
    @Alias("is-business-order")
    private String isBusinessOrder;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "purchase-order-number")
    @Alias("purchase-order-number")
    private String purchaseOrderNumber;

    @ApiModelProperty(value="",example="")
    @JSONField(name = "price-designation")
    @Alias("price-designation")
    private String priceDesignation;
    /**
   * 店铺ID
   */
    @ApiModelProperty(value="店铺ID",example="")
    @JsonIgnore
    private Integer storeId;
    /**
   * 推送状态：0未推送 1已推送
   */
    @ApiModelProperty(value="推送状态：0未推送 1已推送",example="")
    @JsonIgnore
    private Integer pushStatus;

    @ApiModelProperty(value="推送描述",example="")
    @JsonIgnore
    private String pushDescribe;
    /**
   * 创建人
   */
    @ApiModelProperty(value="创建人",example="")
    @JsonIgnore
    private Integer createBy;
    /**
   * 创建时间
   */
    @ApiModelProperty(value="创建时间",example="")
    @JsonIgnore
    private LocalDateTime createTime;
    /**
   * 最后更新人
   */
    @ApiModelProperty(value="最后更新人",example="")
    @JsonIgnore
    private Integer updateBy;
    /**
   * 最后更新时间
   */
    @ApiModelProperty(value="最后更新时间",example="")
    @JsonIgnore
    private LocalDateTime updateTime;
    /**
   * 是否删除 1：已删除  0：正常
   */
    @ApiModelProperty(value="是否删除 1：已删除  0：正常",example="")
    @JsonIgnore
    private String delFlag;
  
}
