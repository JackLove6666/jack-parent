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
 * fba退货主表
 *
 * @author 捷克梗
 * @date 2023-05-09 16:15:09
 */
@Data
@TableName("wms_fba_return")
@ApiModel(value="fba退货主表")
public class WmsFbaReturn extends Model<WmsFbaReturn> {
private static final long serialVersionUID = 1L;

    /**
   * fba退货管理单据编号
   */
    @ApiModelProperty(value="fba退货管理单据编号",example="")
    @TableId
    private String returnOrderNo;
    /**
   * 退货仓
   */
    @ApiModelProperty(value="退货仓",example="")
    private Integer warehouseId;
    /**
   * 运输方式
   */
    @ApiModelProperty(value="运输方式",example="")
    private Integer transportModeId;
    /**
   * fba移除号
   */
    @ApiModelProperty(value="fba移除号",example="")
    private String fbaRemoveNo;
    /**
   * 业务类别：关联wms_business_type表的Id
   */
    @ApiModelProperty(value="业务类别：关联wms_business_type表的Id",example="")
    private Integer handleMode;
    /**
   * 销售组ID
   */
    @ApiModelProperty(value="销售组ID",example="")
    private Integer salesGroupId;
    /**
   * 退货原因
   */
    @ApiModelProperty(value="退货原因",example="")
    private String returnReason;
    /**
   * 是否可售 1：停售  0：可售
   */
    @ApiModelProperty(value="是否可售 1：停售  0：可售",example="")
    private Integer saleStatus;
    /**
   * 单据状态
   */
    @ApiModelProperty(value="单据状态",example="")
    private Integer status;
    /**
   * 备注
   */
    @ApiModelProperty(value="备注",example="")
    private String remark;
    /**
   * 按规则生成的orderID
   */
    @ApiModelProperty(value="按规则生成的orderID",example="")
    private String orderId;
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
