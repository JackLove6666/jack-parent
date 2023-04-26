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
 * 平台类型表
 *
 * @author trobs code generator
 * @date 2023-04-26 15:37:43
 */
@Data
@TableName("bse_platform_type")
@ApiModel(value="平台类型表")
public class BsePlatformType extends Model<BsePlatformType> {
private static final long serialVersionUID = 1L;

    /**
   * 平台类型ID
   */
    @ApiModelProperty(value="平台类型ID",example="")
    @TableId
    private Integer platformTypeId;
    /**
   * 平台类型代码 AMAZON-亚马逊，WISH-WISH，EBAY-EBAY，4PX-4PX
   */
    @ApiModelProperty(value="平台类型代码 AMAZON-亚马逊，WISH-WISH，EBAY-EBAY，4PX-4PX",example="")
    private String platformTypeCode;
    /**
   * 平台类型名称
   */
    @ApiModelProperty(value="平台类型名称",example="")
    private String platformTypeName;
    /**
   * 简码，用于平台生成sku
   */
    @ApiModelProperty(value="简码，用于平台生成sku",example="")
    private String platformTypeShortCode;
    /**
   * 是否参与创建sku
   */
    @ApiModelProperty(value="是否参与创建sku",example="")
    private Integer createSkuFlag;
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
