package com.cloud.jack.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReflectUtil {

    public static Object getObject(Object target, Map<String, Object> addProperties) {
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(target);
        Map<String, Class> propertyMap = new HashMap<>();
        for (PropertyDescriptor descriptor : descriptors) {
            if (!"class".equalsIgnoreCase(descriptor.getName())) {
                propertyMap.put(descriptor.getName(), descriptor.getPropertyType());
            }
        }
        addProperties.forEach((k, v) -> {
            //对日期进行处理
            if (v.getClass().equals(Date.class)) {
                propertyMap.put(k, Long.class);
            } else {
                propertyMap.put(k, v.getClass());
            }
        });
        DynamicBean dynamicBean = new DynamicBean(target.getClass(), propertyMap);
        propertyMap.forEach((k, v) -> {
            try {
                if (!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(target, k));
                }
            } catch (Exception e) {
                log.error("动态添加字段出错", e);
            }
        });
        addProperties.forEach((k, v) -> {
            try {
                //动态添加的字段为date类型需要进行处理
                if (v.getClass().equals(Date.class)) {
                    Date date = (Date) v;
                    dynamicBean.setValue(k, date.getTime());
                } else {
                    dynamicBean.setValue(k, v);
                }
            } catch (Exception e) {
                log.error("动态添加字段值出错", e);
            }
        });
        Object obj = dynamicBean.getTarget();
        return obj;
    }

    /**
     * 判断是否为基本类型
     *
     * @return
     */
    public static boolean isBaseType(Class type) {
        if (String.class.equals(type) ||
                Integer.class.equals(type) ||
                int.class.equals(type) ||
                Byte.class.equals(type) ||
                byte.class.equals(type) ||
                Long.class.equals(type) ||
                long.class.equals(type) ||
                Double.class.equals(type) ||
                double.class.equals(type) ||
                Float.class.equals(type) ||
                float.class.equals(type) ||
                Character.class.equals(type) ||
                char.class.equals(type) ||
                Short.class.equals(type) ||
                short.class.equals(type) ||
                Boolean.class.equals(type) ||
                boolean.class.equals(type)) {
            return true;
        }
        return false;
    }

    public static boolean isDateType(Class type) {
        if (Date.class.equals(type)) {
            return true;
        }
        return false;
    }


}
