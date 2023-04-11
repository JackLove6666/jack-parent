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
package com.cloud.jack.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 源数据报告类型
 *
 * @author trobs code generator
 * @date 2022-10-11 14:56:08
 */
@Data
@TableName("source_bill_type")
@ApiModel(value="源数据报告类型")
public class SourceBillType extends Model<SourceBillType> {
private static final long serialVersionUID = 1L;

    /**
   * 报告类型
   */
    @ApiModelProperty(value="报告类型",example="")
    @TableId(type= IdType.INPUT)
    private String billType;
    /**
   * 报告类型名称
   */
    @ApiModelProperty(value="报告类型名称",example="")
    private String billTypeName;
    /**
   * 推送mq
   */
    @ApiModelProperty(value="推送mq",example="")
    private Long sourceMqId;
    /**
   * 名字正则校验
   */
    @ApiModelProperty(value="名字正则校验",example="")
    private String nameRegex;

    private String placeholder;
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
  
}
