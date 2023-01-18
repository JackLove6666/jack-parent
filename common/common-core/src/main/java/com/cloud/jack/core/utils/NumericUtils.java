package com.cloud.jack.core.utils;

import java.text.NumberFormat;

/**
 * 数字工具类
 * 
 * @author y.yh
 */
public class NumericUtils {

    /**
     * 将一个数或字符串对象转化为字符串 例: "123" 、 "123.6" 、123、123.6
     * 
     * @param obj
     * @return
     */
    public static String formatObjectToStr(Object obj) {
        if (obj == null || obj == "") {
            return "";
        }
        // 1.如果对象是字符串，直接转为字符串
        if (obj instanceof String) {
            return obj.toString();
        }
        // 2.是数执行
        if (obj instanceof Number) {
            NumberFormat nf = NumberFormat.getInstance();
            // 千位不用逗号分隔
            nf.setGroupingUsed(false);
            try {
                return nf.format(obj);
            } catch (Exception e) {
            }
        }
        // 3.其他对象转换
        return obj.toString();
    }

    /**
     * 验证字符串是否是数 12位正数字 小数2位
     * 
     * @param amount
     */
    public static boolean validAmount(String amount) {
        return amount.matches("^\\d{1,12}\\.?\\d{0,2}$");
    }

    /**
     * 验证字符串是否是数 12位数字 小数2位
     * 
     * @param amount
     */
    public static boolean validAmountPM(String amount) {
        return amount.matches("^[-\\+]?\\d{1,12}\\.?\\d{0,2}$");
    }

    public static String validAmountToMsg(String amount) {
        if (!validAmount(amount)) {
            return "金额值为：1~12位正整数，0~2位小数";
        }
        return null;
    }

    public static String validAmountPMToMsg(String amount) {
        if (!validAmountPM(amount)) {
            return "金额值为：1~12位整数，0~2位小数";
        }
        return null;
    }
}
