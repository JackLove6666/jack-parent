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
 * 国家地区表
 *
 * @author trobs code generator
 * @date 2023-04-26 15:38:15
 */
@Data
@TableName("bse_country")
@ApiModel(value="国家地区表")
public class BseCountry extends Model<BseCountry> {
private static final long serialVersionUID = 1L;

    /**
   * 国家ID
   */
    @ApiModelProperty(value="国家ID",example="")
    @TableId
    private Integer countryId;
    /**
   * 国家二字代码
   */
    @ApiModelProperty(value="国家二字代码",example="")
    private String countryCode;
    /**
   * 国家名称
   */
    @ApiModelProperty(value="国家名称",example="")
    private String countryName;
    /**
   * 国家简称
   */
    @ApiModelProperty(value="国家简称",example="")
    private String countryShortName;
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
