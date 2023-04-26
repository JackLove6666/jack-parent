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
 * 店铺表
 *
 * @author trobs code generator
 * @date 2023-04-26 15:38:43
 */
@Data
@TableName("bse_store")
@ApiModel(value="店铺表")
public class BseStore extends Model<BseStore> {
private static final long serialVersionUID = 1L;

    /**
   * 店铺ID
   */
    @ApiModelProperty(value="店铺ID",example="")
    @TableId
    private Integer storeId;
    /**
   * 平台id
   */
    @ApiModelProperty(value="平台id",example="")
    private Integer platformTypeId;
    /**
   * 平台站点ID
   */
    @ApiModelProperty(value="平台站点ID",example="")
    private Integer bsePlatformSitId;
    /**
   * 店铺账号(店铺名称)
   */
    @ApiModelProperty(value="店铺账号(店铺名称)",example="")
    private String storeAccount;
    /**
   * 英文名称
   */
    @ApiModelProperty(value="英文名称",example="")
    private String storeNameEn;
    /**
   * 店铺简称
   */
    @ApiModelProperty(value="店铺简称",example="")
    private String storeNameShort;
    /**
   * 销售组id
   */
    @ApiModelProperty(value="销售组id",example="")
    private Integer saleGroupId;
    /**
   * seller_id
   */
    @ApiModelProperty(value="seller_id",example="")
    private String sellerId;
    /**
   * 法人主体id
   */
    @ApiModelProperty(value="法人主体id",example="")
    private Integer corporationId;
    /**
   * 法人主体开关，供自发货使用0限制法人发货，1不限制法人发货
   */
    @ApiModelProperty(value="法人主体开关，供自发货使用0限制法人发货，1不限制法人发货",example="")
    private Integer corporationSwitch;
    /**
   * 店铺公司名
   */
    @ApiModelProperty(value="店铺公司名",example="")
    private String storeCompanyName;
    /**
   * token
   */
    @ApiModelProperty(value="token",example="")
    private String token;
    /**
   * 账号状态 0正常 1禁用
   */
    @ApiModelProperty(value="账号状态 0正常 1禁用",example="")
    private String state;
    /**
   * 备注
   */
    @ApiModelProperty(value="备注",example="")
    private String remark;
    /**
   * 是否VAT合规 1是  0 否
   */
    @ApiModelProperty(value="是否VAT合规 1是  0 否",example="")
    private Integer isVatCompliance;
    /**
   * 是否税号已注销的标识 1是  0 否
   */
    @ApiModelProperty(value="是否税号已注销的标识 1是  0 否",example="")
    private Integer isVatTax;
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
