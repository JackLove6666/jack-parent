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

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * fba退货导入
 */
@Data
public class WmsFbaReturnImportExcelVo {

    @ExcelProperty("销售组")
    private String salesGroupName;

    @ExcelProperty("退货平台")
    private String returnPlatformType;

    @ExcelProperty("退货站点")
    private String returnPlatformSite;

    @ExcelProperty("退货店铺")
    private String returnStore;

    @ExcelProperty("是否可售")
    private String saleStatus;

    @ExcelProperty("运输方式")
    private String transportModeName;

    @ExcelProperty("处理方式")
    private String handleMode;

    @ExcelProperty("退货原因")
    private String returnReason;

    @ExcelProperty("平台SKU")
    private String bsePlatformSkuCode;

    @ExcelProperty("申请数量")
    private String returnQuantity;

    @ExcelProperty("备注")
    private String remark;

    @ExcelIgnore
    private Integer returnPlatformSiteId;

    @ExcelIgnore
    private Integer returnStoreId;

    @ExcelIgnore
    private Integer handleModeId;

    @ExcelIgnore
    private Integer salesGroupId;

    @ExcelIgnore
    private Integer transportModeId;

    @ExcelIgnore
    private Integer bsePlatformSkuId;

}
