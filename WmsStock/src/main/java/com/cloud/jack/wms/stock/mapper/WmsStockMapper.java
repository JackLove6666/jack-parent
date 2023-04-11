package com.cloud.jack.wms.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.jack.wms.stock.entity.WmsStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WmsStockMapper extends BaseMapper<WmsStock> {

    @Update("update wms_stock set stock_quantity = stock_quantity -1 where product_code = #{productCode} and stock_quantity > 0")
    int updateStock(String productCode);

}
