package com.cloud.jack.app.test;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Slf4j
public class VolumeTest {

    static class PackageProductInfoSku {
        @ApiModelProperty(value = "数量", example = "")
        private Integer count;

        /**
         * 长
         */
        private String length;

        /**
         * 宽
         */
        private String width;

        /**
         * 高
         */
        private String height;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }


    public static void main(String[] args) {
        PackageProductInfoSku packageProductInfoSku = new PackageProductInfoSku();
        packageProductInfoSku.setCount(1);
        packageProductInfoSku.setHeight("40");
        packageProductInfoSku.setLength("30");
        packageProductInfoSku.setWidth("20");

        PackageProductInfoSku packageProductInfoSku1 = new PackageProductInfoSku();
        packageProductInfoSku1.setCount(null);
        packageProductInfoSku1.setHeight("40");
        packageProductInfoSku1.setLength("30");
        packageProductInfoSku1.setWidth("20");

        PackageProductInfoSku packageProductInfoSku2 = new PackageProductInfoSku();
        packageProductInfoSku2.setCount(3);
        packageProductInfoSku2.setHeight("40");
        packageProductInfoSku2.setLength("30");
        packageProductInfoSku2.setWidth("20");

        List<PackageProductInfoSku> productInfoSkuList = new ArrayList<>();
        productInfoSkuList.add(packageProductInfoSku);
        productInfoSkuList.add(packageProductInfoSku1);
        productInfoSkuList.add(packageProductInfoSku2);

        double length = 0d;
        double width = 0d;
        double height = 0d;
        double volume = 0d;
        int maxCount = productInfoSkuList.stream().filter(item -> item.getCount() != null).
                max(Comparator.comparing(PackageProductInfoSku::getCount)).get().getCount();
        for (PackageProductInfoSku sku : productInfoSkuList) {
            // 长度
            Double skuLength = Double.parseDouble(sku.getLength());
            length += skuLength;
            // 宽度
            Double skuWidth = Double.parseDouble(sku.getWidth());
            width += skuWidth;
            // 高度
            Double skuHeight = Double.parseDouble(sku.getHeight());
            height += skuHeight;
        }
        volume = maxCount * length * (width * height);

        log.info("包裹的信息: 长-[{}] 宽-[{}] 高-[{}] 体积-[{}]"
                , maxCount * length, width, height, volume);
    }
}
