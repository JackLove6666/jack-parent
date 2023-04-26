package com.cloud.jack.app.entity;

import cn.hutool.core.annotation.Alias;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class RemoveOrderData {

    @ExcelProperty(value = "request-date")
    @Alias("request-date")
    private String requestDate;

    @ExcelProperty(value = "order-id")
    @Alias("order-id")
    private String orderId;

    @ExcelProperty(value = "order-type")
    @Alias("order-type")
    private String orderType;

    @ExcelProperty(value = "service-speed")
    @Alias("service-speed")
    private String serviceSpeed;

    @ExcelProperty(value = "order-status")
    @Alias("order-status")
    private String orderStatus;

    @ExcelProperty(value = "last-updated-date")
    @Alias("last-updated-date")
    private String lastUpdatedDate;

    @ExcelProperty(value = "sku")
    @Alias("sku")
    private String sku;

    @ExcelProperty(value = "fnsku")
    @Alias("fnsku")
    private String fnsku;

    @ExcelProperty(value = "disposition")
    @Alias("disposition")
    private String disposition;

    @ExcelProperty(value = "requested-quantity")
    @Alias("requested-quantity")
    private Integer requestedQuantity;

    @ExcelProperty(value = "cancelled-quantity")
    @Alias("cancelled-quantity")
    private Integer cancelledQuantity;

    @ExcelProperty(value = "disposed-quantity")
    @Alias("disposed-quantity")
    private Integer disposedQuantity;

    @ExcelProperty(value = "shipped-quantity")
    @Alias("shipped-quantity")
    private Integer shippedQuantity;

    @ExcelProperty(value = "in-process-quantity")
    @Alias("in-process-quantity")
    private Integer inProcessQuantity;

    @ExcelProperty(value = "removal-fee")
    @Alias("removal-fee")
    private String removalFee;

    @ExcelProperty(value = "currency")
    @Alias("currency")
    private String currency;

}
