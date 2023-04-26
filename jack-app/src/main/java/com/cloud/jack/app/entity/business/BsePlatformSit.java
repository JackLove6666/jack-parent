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
 * 平台站点表
 *
 * @author trobs code generator
 * @date 2023-04-26 15:35:37
 */
@Data
@TableName("bse_platform_sit")
@ApiModel(value="平台站点表")
public class BsePlatformSit extends Model<BsePlatformSit> {
private static final long serialVersionUID = 1L;

    /**
   * 平台站点ID
   */
    @ApiModelProperty(value="平台站点ID",example="")
    @TableId
    private Integer bsePlatformSitId;
    /**
   * 平台类型 （平台类型表:platform_type_id）
   */
    @ApiModelProperty(value="平台类型 （平台类型表:platform_type_id）",example="")
    private Integer sitType;
    /**
   * 平台站点名称
   */
    @ApiModelProperty(value="平台站点名称",example="")
    private String sitName;
    /**
   * 站点网站
   */
    @ApiModelProperty(value="站点网站",example="")
    private String sitUrl;
    /**
   * 站点地址简写
   */
    @ApiModelProperty(value="站点地址简写",example="")
    private String sitUrlShort;
    /**
   * 所属国家
   */
    @ApiModelProperty(value="所属国家",example="")
    private Integer countryId;
    /**
   * 所属区域
   */
    @ApiModelProperty(value="所属区域",example="")
    private Integer districtId;
    /**
   * 站点关键信息1
   */
    @ApiModelProperty(value="站点关键信息1",example="")
    private String marketplaceId;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String awsAccessKeyId;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String secretKey;
    /**
   * 平台站点说明
   */
    @ApiModelProperty(value="平台站点说明",example="")
    private String remarks;
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
