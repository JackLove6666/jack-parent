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
package com.cloud.jack.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.jack.admin.entity.SysUser;
import com.cloud.jack.admin.service.SysUserService;
import com.cloud.jack.core.R;
import com.cloud.jack.core.annotation.ApiRateLimiter;
import com.cloud.jack.core.validate.CommonCheckColumn;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



/**
 * 用户
 *
 * @author 捷克梗
 * @date 2022-12-14 16:38:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysuser")
@Api(value = "sysuser", description = "用户模块")
public class SysUserController {

  private final SysUserService sysUserService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param sysUser 用户
   * @return
   */
  @ApiOperation(value="分页查询")
  @GetMapping("/page")
  @ApiRateLimiter(confKey = "getSysUserPage", value = "")
  public R getSysUserPage(@ApiParam(name="page",value="分页信息",required=true) Page page,@ApiParam(name="sysUser",value="用户",required=true) SysUser sysUser) {
    return  new R<>(sysUserService.page(page,Wrappers.query(sysUser)));
  }


  /**
   * 通过id查询用户
   * @param userId id
   * @return R
   */
  @ApiOperation(value="通过id查询用户") 
  @GetMapping("/{userId}")
  public R getById(@ApiParam(name="userId",value="用户userId",required=true) @PathVariable("userId") Integer userId){
    return new R<>(sysUserService.getById(userId));
  }

  /**
   * 新增用户
   * @param sysUser 用户表
   * @return R
   */
  @ApiOperation(value="新增用户") 
  @PostMapping
  public R save(@ApiParam(name="sysUser",value="用户",required=true) @RequestBody SysUser sysUser){

    String checkFieldReturnMsg = CommonCheckColumn.checkFieldReturnMsg(sysUser);
    //检查字段
    if(StrUtil.isNotBlank(checkFieldReturnMsg)){
       return R.fail(checkFieldReturnMsg);
    }
    return new R<>(sysUserService.save(sysUser));
  }

  /**
   * 修改用户
   * @param sysUser 用户
   * @return R
   */

  @ApiOperation(value="修改用户") 
  @PutMapping
  public R updateById(@ApiParam(name="sysUser",value="用户",required=true) @RequestBody SysUser sysUser){
    return new R<>(sysUserService.updateById(sysUser));
  }

  /**
   * 通过id删除用户
   * @param userId id
   * @return R
   */

  @ApiOperation(value="删除用户")
  @DeleteMapping("/{userId}")
  public R removeById(@ApiParam(name="userId",value="用户userId",required=true) @PathVariable Integer userId){
    return new R<>(sysUserService.removeById(userId));
  }

}
