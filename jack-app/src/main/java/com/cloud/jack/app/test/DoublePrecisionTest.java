package com.cloud.jack.app.test;

import java.math.BigDecimal;

public class DoublePrecisionTest {


    public static void main(String[] args) {
        Double boxGrossWeight = 3776.5;
        Double skuGrossWeight = 10.79;
        Integer boxs = 350;

        Double  totalGrossWeight = skuGrossWeight * boxs;
        System.out.println(totalGrossWeight);

        //使用BigDecimal
        BigDecimal boxGross = new BigDecimal(boxGrossWeight.toString());
        BigDecimal skuGross = new BigDecimal(skuGrossWeight.toString());
        int compareTo = skuGross.multiply(new BigDecimal(boxs)).compareTo(boxGross);
        System.out.println(compareTo);
        if(compareTo >= 0){
            System.out.println("产品毛重超出箱子毛重");
        }
    }
}
