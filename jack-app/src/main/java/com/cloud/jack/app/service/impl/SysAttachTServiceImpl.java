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
package com.cloud.jack.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.jack.app.entity.AttachEntity;
import com.cloud.jack.app.mapper.SysAttachTMapper;
import com.cloud.jack.app.service.SysAttachTService;
import org.springframework.stereotype.Service;

/**
 * 系统附件表
 *
 * @author 捷克梗
 * @date 2023-01-20 10:34:18
 */
@Service("sysAttachTService")
public class SysAttachTServiceImpl extends ServiceImpl<SysAttachTMapper, AttachEntity> implements SysAttachTService {

}
