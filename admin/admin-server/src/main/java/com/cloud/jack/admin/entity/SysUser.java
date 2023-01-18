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
package com.cloud.jack.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.jack.core.annotation.CheckColumn;
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
 * 用户表
 *
 * @author 捷克梗
 * @date 2023-01-17 14:52:26
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("sys_user")
@ApiModel(value="用户表")
public class SysUser extends Model<SysUser> {
private static final long serialVersionUID = 1L;

    /**
   * 主键ID
   */
    @ApiModelProperty(value="主键ID",example="")
    @TableId
    private Integer userId;
    /**
   * 账号
   */
    @ApiModelProperty(value="账号",example="")
    @CheckColumn(columnName = "username",required = true)
    private String username;
    /**
   * 
   */
    @ApiModelProperty(value="",example="")
    private String password;
    /**
   * 用户姓名
   */
    @ApiModelProperty(value="用户姓名",example="")
    private String displayname;
    /**
   * 邮箱
   */
    @ApiModelProperty(value="邮箱",example="")
    private String email;
    /**
   * 性别：0 男   1 女
   */
    @ApiModelProperty(value="性别：0 男   1 女",example="")
    private String genders;
    /**
   * 随机盐
   */
    @ApiModelProperty(value="随机盐",example="")
    private String salt;
    /**
   * 电话
   */
    @ApiModelProperty(value="电话",example="")
    private String phone;
    /**
   * 头像
   */
    @ApiModelProperty(value="头像",example="")
    private String avatar;
    /**
   * 部门ID
   */
    @ApiModelProperty(value="部门ID",example="")
    private Integer deptId;
    /**
   * 创建时间
   */
    @ApiModelProperty(value="创建时间",example="")
    private LocalDateTime createTime;
    /**
   * 修改时间
   */
    @ApiModelProperty(value="修改时间",example="")
    private LocalDateTime updateTime;
    /**
   * 0-正常，1-锁定
   */
    @ApiModelProperty(value="0-正常，1-锁定",example="")
    private String lockFlag;
    /**
   * 0-正常，1-删除
   */
    @ApiModelProperty(value="0-正常，1-删除",example="")
    private String delFlag;
    /**
   * 微信openid
   */
    @ApiModelProperty(value="微信openid",example="")
    private String wxOpenid;
    /**
   * QQ openid
   */
    @ApiModelProperty(value="QQ openid",example="")
    private String qqOpenid;
    /**
   * 创建人
   */
    @ApiModelProperty(value="创建人",example="")
    private Integer createBy;
    /**
   * 最后更新人
   */
    @ApiModelProperty(value="最后更新人",example="")
    private Integer updateBy;
    /**
   * 租户
   */
    @ApiModelProperty(value="租户",example="")
    private Integer tenantId;
  
}
