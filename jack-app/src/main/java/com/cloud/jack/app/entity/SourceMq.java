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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 源数据mq
 *
 * @author 捷克梗
 * @date 2023-01-19 10:43:23
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("source_mq")
@ApiModel(value="源数据mq")
public class SourceMq extends Model<SourceMq> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @ApiModelProperty(value="主键id",example="")
    @TableId
    private Long sourceMqId;
    /**
   * 队列名
   */
    @ApiModelProperty(value="队列名",example="")
    private String queueName;
    /**
   * 交换机名
   */
    @ApiModelProperty(value="交换机名",example="")
    private String exchangeName;
    /**
   * 绑定key
   */
    @ApiModelProperty(value="绑定key",example="")
    private String bindKey;
    /**
   * 死信队列名
   */
    @ApiModelProperty(value="死信队列名",example="")
    private String deadLetterQueueName;
    /**
   * 死信交换机名
   */
    @ApiModelProperty(value="死信交换机名",example="")
    private String deadLetterExchangeName;
    /**
   * 死信绑定key
   */
    @ApiModelProperty(value="死信绑定key",example="")
    private String deadLetterBindKey;
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
