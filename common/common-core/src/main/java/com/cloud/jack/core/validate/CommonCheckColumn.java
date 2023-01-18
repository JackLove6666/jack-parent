package com.cloud.jack.core.validate;

import cn.hutool.core.util.StrUtil;
import com.cloud.jack.core.annotation.CheckColumn;
import com.cloud.jack.core.utils.NumericUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CommonCheckColumn {

        public static <T> String checkFieldReturnMsg(T t){
            if(t == null){
                return null;
            }else {
                Class<?> aClass = t.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                StringBuilder result = new StringBuilder();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    CheckColumn checkColumn = field.getAnnotation(CheckColumn.class);
                    if(checkColumn != null){
                        String msg = checkColumn(t, field, checkColumn);
                        if(StrUtil.isNotBlank(msg)){
                            String columnName = checkColumn.columnName();
                            result.append(String.format("【%s：%s】",columnName,msg));
                        }
                    }
                }
                return result.toString();
            }
        }


        private static <T> String checkColumn(T t, Field field, CheckColumn checkColumn)  {
            Object value;//属性值
            try {
                value = field.get(t);
                if(value == null){
                    value = "";
                }
            }catch (IllegalAccessException e){
                value = "";
            }
            String valid = NumericUtils.formatObjectToStr(value);
            boolean required = checkColumn.required();//注解值
            //非空
            if(StrUtil.isBlank(valid)){
                if(required){
                    return "不能为空";
                }else {
                    return  valid;
                }
            }
            //金额限制
            int amountLimit = checkColumn.amountLimit();
            if(amountLimit >= 0){
                String toMsg = NumericUtils.validAmountPMToMsg(valid);
                if(toMsg != null){
                    return  toMsg;
                }
            }
            //最大长度
            int maxLength = checkColumn.maxLength();
            if(maxLength > 0){
                if(valid.length() > maxLength){
                    return String.format("最大长度为%s",maxLength);
                }
            }
            //数字
            boolean numeric = checkColumn.isNumeric();
            if(numeric){
                boolean isInteger = StringUtils.isNumeric(valid);
                if(!isInteger){
                    return String.format("必须为正整数");
                }
            }
            //一个数
            boolean number = checkColumn.isNumber();
            if(number){

                boolean isNum = valid.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$");
                if(!isNum){
                    return String.format("必须为小数或整数");
                }
            }

            // 最大值限制
            int maxLimit = checkColumn.maxLimit();
            if(maxLimit != -1){
                if(new BigDecimal(valid).compareTo(new BigDecimal(maxLimit)) > 0){
                    return String.format("（值：%s）> %s",valid,maxLimit);
                }
            }

            // 最小值限制
            int minLimit = checkColumn.minLimit();
            if (minLimit != -1) {
                if (new BigDecimal(valid).compareTo(new BigDecimal(minLimit)) < 0) {
                    return String.format("（值：%s）< %s", valid, minLimit);
                }
            }
            //最大保留位数
            int maxKeepDigit = checkColumn.maxKeepDigit();
            if(maxKeepDigit != -1){
                if(valid.split("\\.").length == 2){
                    if(valid.split("\\.")[1].length() >maxKeepDigit){
                        return String.format("（值：%s）保留%s位小数",valid,maxKeepDigit);
                    }
                }
            }

            return "";

        }
}
