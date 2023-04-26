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
 * 平台SKU信息表
 *
 * @author trobs code generator
 * @date 2023-04-26 15:35:21
 */
@Data
@TableName("bse_platform_sku")
@ApiModel(value="平台SKU信息表")
public class BsePlatformSku extends Model<BsePlatformSku> {
private static final long serialVersionUID = 1L;

    /**
   * 平台SKU_ID
   */
    @ApiModelProperty(value="平台SKU_ID",example="")
    @TableId
    private Integer bsePlatformSkuId;
    /**
   * 平台SKU
   */
    @ApiModelProperty(value="平台SKU",example="")
    private String platformSkuCode;
    /**
   * 平台类型ID
   */
    @ApiModelProperty(value="平台类型ID",example="")
    private Integer platformTypeId;
    /**
   * 平台站点id
   */
    @ApiModelProperty(value="平台站点id",example="")
    private Integer bsePlatformSitId;
    /**
   * 店铺id
   */
    @ApiModelProperty(value="店铺id",example="")
    private Integer storeId;
    /**
   * SKU_ID
   */
    @ApiModelProperty(value="SKU_ID",example="")
    private Integer skuId;
    /**
   * 待审核修改sku
   */
    @ApiModelProperty(value="待审核修改sku",example="")
    private Integer skuIdBak;
    /**
   * 仓库ID
   */
    @ApiModelProperty(value="仓库ID",example="")
    private Integer warehouseId;
    /**
   * 业务组ID
   */
    @ApiModelProperty(value="业务组ID",example="")
    private Integer salesGroupId;
    /**
   * 待审核的销售组
   */
    @ApiModelProperty(value="待审核的销售组",example="")
    private Integer salesGroupIdBak;
    /**
   * 修改原因
   */
    @ApiModelProperty(value="修改原因",example="")
    private String editReason;
    /**
   * 审核状态 1-待审核，2-已审核,3物流审核
   */
    @ApiModelProperty(value="审核状态 1-待审核，2-已审核,3物流审核",example="")
    private Integer approveStatus;
    /**
   * 负责人
   */
    @ApiModelProperty(value="负责人",example="")
    private Integer chargeUserId;
    /**
   * asin
   */
    @ApiModelProperty(value="asin",example="")
    private String asin;
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
   * 是否启用 1：禁用  0：启用
   */
    @ApiModelProperty(value="是否启用 1：禁用  0：启用",example="")
    private String delFlag;
  
}
