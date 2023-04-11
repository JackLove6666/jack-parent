package com.cloud.jack.wms.stock.entity;

import lombok.Data;

@Data
public class WmsStock {

    private Integer id;

    private String productCode;

    private String warehouseCode;

    private Integer stockQuantity;

    private Integer version;
}
