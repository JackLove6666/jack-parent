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
 * Author: wangbin (AukeyIT@aukeys.com)
 */
package com.cloud.jack.app.entity.vo.fbaReturn;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

/**
 * fba退货导入
 */
@Data
public class WmsFbaReturnImportCheckVo {
    private Map<String, Integer> sysGroupMap;
    private Map<Pair<String, String>, Integer> bsePlatformSitMap;
    private Map<Pair<Integer, String>, Integer> storeMap;
    private Map<String, Integer> bseTransportModesMap;
    private Map<String, Integer> wmsBusinessTypeMap;
    private Map<String, Integer> platformSkuMap;
}
