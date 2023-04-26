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
package com.cloud.jack.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.jack.app.entity.business.BseStore;
import com.cloud.jack.app.mapper.BseStoreMapper;
import com.cloud.jack.app.service.BseStoreService;
import org.springframework.stereotype.Service;

/**
 * 店铺表
 *
 * @author trobs code generator
 * @date 2023-04-26 15:38:43
 */
@Service("bseStoreService")
public class BseStoreServiceImpl extends ServiceImpl<BseStoreMapper, BseStore> implements BseStoreService {

}
