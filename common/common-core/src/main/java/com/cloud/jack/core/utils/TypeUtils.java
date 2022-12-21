package com.cloud.jack.core.utils;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类型转换工具
 *
 * @author 花叶猴
 * @date 2020/12/2 11:36
 **/
public class TypeUtils {


    /**
     * String 转化为 日期  java.util.Date
     *
     * @param value String
     * @return Date对象
     * @throws Exception
     */
    public static Date StringToDate(String value) {
        return DateFormatFactory.format(value);
    }


    /**
     * 将object 转化为 日期  java.util.Date
     *
     * @param value object对象
     * @return Date对象
     * @throws Exception
     */
    public static Date castToDate(Object value) throws Exception {
        return castToDate(value, null);
    }

    /**
     * 将object 转化为 日期  java.util.Date
     *
     * @param value  object现象
     * @param format 解析格式
     * @return Date对象
     * @throws Exception
     */
    public static Date castToDate(Object value, String format) throws Exception {

        if (value == null) {
            return null;
        }

        // 使用频率最高的，应优先处理
        if (value instanceof Date) {
            return (Date) value;
        }

        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }

        long longValue = -1;

        if (value instanceof BigDecimal) {
            longValue = longValue((BigDecimal) value);
            return new Date(longValue);
        }

        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
            if ("unixtime".equals(format)) {
                longValue *= 1000;
            }
            return new Date(longValue);
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.indexOf(':') > 0 || strVal.indexOf('-') > 0 || strVal.indexOf('.') > 0 || strVal.indexOf('/') > 0 || strVal.indexOf('+') > 0 || format != null) {
                if (format == null) {
                    final int len = strVal.length();
                    if (len == 7 && strVal.contains("/")) {
                        format = "yyyy/MM";
                    } else if (len == 7 && strVal.contains("-")) {
                        format = "yyyy-MM";
                    } else if (len == 8) {
                        format = "yy/MM/dd";
                    } else if (len == 10 && strVal.contains("-")) {
                        format = "yyyy-MM-dd";
                    } else if (len == 10 && strVal.contains("/") && strVal.indexOf("/") > 3) {
                        format = "yyyy/MM/dd";
                    } else if (len == 10 && strVal.contains("/") && strVal.indexOf("/") <= 3) {
                        format = "MM/dd/yyyy";
                    } else if (len == "yyyy-MM-dd HH:mm:ss".length()) {
                        format = "yyyy-MM-dd HH:mm:ss";
                    } else if (len == 29
                            && strVal.charAt(26) == ':'
                            && strVal.charAt(28) == '0') {
                        strVal = strVal.substring(0, strVal.lastIndexOf("."));
//                        format = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
                        format = "yyyy-MM-dd'T'HH:mm:ss";
                    } else if (len == 23 && strVal.charAt(19) == ',') {
                        strVal = strVal.substring(0, strVal.lastIndexOf(","));
//                        format = "yyyy-MM-dd HH:mm:ss,SSS";
                        format = "yyyy-MM-dd HH:mm:ss";
                    } else if (len > 19 && strVal.substring(0, 15).contains("T") && (strVal.substring(13).contains("+") || strVal.substring(13).contains("-"))) {
                        strVal = strVal.replace("T", " ");
                        if (strVal.contains("-")) {
                            strVal.substring(0, strVal.lastIndexOf("-"));
                        } else if (strVal.contains("+")) {
                            strVal.substring(0, strVal.lastIndexOf("+"));
                        }
//                        format = "yyyy-MM-dd'T'HH:mm:ssXXX";
                        format = "yyyy-MM-dd HH:mm:ss";
                    } else if (strVal.contains("/") &&
                            (strVal.contains("AM") || strVal.contains("PM"))) {
                        strVal = strVal.substring(0, strVal.lastIndexOf(" "));
//                        format = "MM/dd/yy HH:mm:ss a zzzz";
                        format = "MM/dd/yy hh:mm:ss aa";
                    } else if (strVal.contains(".") && !strVal.contains(":")) {
                        format = "MM.dd.yyyy";
                    } else if (strVal.contains(".") && strVal.contains(":")) {
//                        format = "dd.MM.yyyy HH:mm:ss zzzz";
                        strVal = strVal.substring(0, strVal.lastIndexOf(" "));
                        format = "dd.MM.yyyy HH:mm:ss";
                    } else if (strVal.contains("-") && len > 20) {
                        strVal = strVal.substring(0, strVal.lastIndexOf(" "));
//                        format = "yyyy-MM-dd HH:mm:ss zzzz";
                        format = "yyyy-MM-dd HH:mm:ss";
                    } else if (strVal.contains("/") && len > 20 && strVal.indexOf("/") < 3) {
                        strVal = strVal.substring(0, strVal.lastIndexOf(" "));
//                        format = "MM/dd/yyyy HH:mm:ss zzzz";
                        format = "MM/dd/yyyy HH:mm:ss";
                    } else if (strVal.contains("/") && len > 20) {
                        strVal = strVal.substring(0, strVal.lastIndexOf(" "));
//                        format = "MM/dd/yyyy HH:mm:ss zzzz";
                        format = "yyyy/MM/dd HH:mm:ss";
                    } else if (strVal.charAt(0) >= 65 && strVal.charAt(0) <= 90) {
                        String[] s = strVal.split(" ");
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < s.length; i++) {
                            if (i != 4) {
                                builder.append(s[i]).append(" ");
                            }
                        }
                        strVal = builder.toString();
                        format = "EEEE MMMM dd hh:mm:ss yyyy";
                    } else if (strVal.contains("-") && len < 20) {
                        strVal = strVal.substring(0, strVal.indexOf(" "));
                        format = "dd-MMMM-yyyy";
                    } else {
                        throw new RuntimeException(String.format("%s解析失败", strVal));
                    }
                }
                DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
                try {
                    return dateFormat.parse(strVal);
                } catch (ParseException e) {
                    throw new ParseException("can not cast to Date, value : " + strVal, 0);
                }
            }
            if (strVal.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(strVal);
        }


        return new Date(longValue);
    }

    /**
     * 获取BigDecimal的longValue
     *
     * @param decimal Bigdecimal对象
     * @return longValue
     */
    public static long longValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0;
        }

        int scale = decimal.scale();
        if (scale >= -100 && scale <= 100) {
            return decimal.longValue();
        }

        return decimal.longValueExact();
    }

    public static Date formatDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        if (hour == 0) {
            return date;
        }
        if (hour >= 12) {
            instance.set(Calendar.HOUR_OF_DAY, 0);
            instance.add(Calendar.DAY_OF_MONTH, 1);
        } else {
            instance.set(Calendar.HOUR, 0);
        }
        return instance.getTime();
    }

    /**
     * 类型转换工厂
     */
    private static class DateFormatFactory {

        private static Map<String, Function<String, Date>> pattenMap = new HashMap<>();

        static {
            // 02-Dec-2020 UTC
            pattenMap.put("^\\d{1,2}-\\w+-\\d{4}\\s?\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.indexOf(" "));
                        return stringToDate(dateString, "dd-MMMM-yyyy");
                    });

            // 2020-11-29T00:00:00+00:00
            pattenMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}(\\+|\\-)\\d{1,2}:\\d{1,2}$",
                    dateString -> {
                        // 截取字符串
                        if (dateString.contains("+")) {
                            dateString = dateString.substring(0, dateString.indexOf("+"));
                        } else {
                            dateString = dateString.substring(0, dateString.lastIndexOf("-"));
                        }
                        return stringToDate(dateString, "yyyy-MM-dd'T'HH:mm:ss");
                    });

            // Sat Mar 07 06:39:23 UTC 2020
            pattenMap.put("^\\w+\\s?\\w+\\s?\\d{1,2}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?\\w+\\s?\\d{4}$",
                    dateString -> {
                        // 截取字符串
                        String[] s = dateString.split(" ");
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < s.length; i++) {
                            if (i != 4) {
                                builder.append(s[i]).append(" ");
                            }
                        }
                        dateString = builder.toString();
                        return stringToDate(dateString, "EEEE MMMM dd hh:mm:ss yyyy");
                    });

            // 10/09/2020 12:41:57 MEST
            pattenMap.put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.lastIndexOf(" "));
                        return stringToDate(dateString, "MM/dd/yyyy HH:mm:ss");
                    });

            // 2018-07-25 16:43:42 PDT
            pattenMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.lastIndexOf(" "));
                        return stringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
                    });

            // 31.08.2020 04:20:28 UTC
            pattenMap.put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.lastIndexOf(" "));
                        return stringToDate(dateString, "dd.MM.yyyy HH:mm:ss");
                    });

            // 10/8/20 11:19:36 PM MEST
            pattenMap.put("^\\d{1,2}/\\d{1,2}/\\d{1,2}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?(AM|PM)\\s?\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.lastIndexOf(" "));
                        return stringToDate(dateString, "MM/dd/yy hh:mm:ss aa");
                    });

            // 01.12.2020 05:15:19 Europe/Berlin
            pattenMap.put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?\\w+/\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.lastIndexOf(" "));
                        return stringToDate(dateString, "dd.MM.yyyy HH:mm:ss");
                    });

            // 2020/09/12 10:23:59 UTC
            pattenMap.put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.lastIndexOf(" "));
                        return stringToDate(dateString, "yyyy/MM/dd HH:mm:ss");
                    });

            // 12/18/2020
            pattenMap.put("^\\d{1,2}/\\d{1,2}/\\d{4}$",
                    dateString -> stringToDate(dateString, "MM/dd/yyyy"));

            // 12-06-2020
            pattenMap.put("^\\d{1,2}-\\d{1,2}-\\d{4}$",
                    dateString -> stringToDate(dateString, "MM-dd-yyyy"));

            // 08.21.2020
            pattenMap.put("^\\d{1,2}.\\d{1,2}.\\d{4}$",
                    dateString -> stringToDate(dateString, "MM.dd.yyyy"));

            // 2020-07
            pattenMap.put("^\\d{4}-\\d{1,2}$",
                    dateString -> stringToDate(dateString, "yyyy-MM"));

            // 2020/04
            pattenMap.put("^\\d{4}/\\d{1,2}$",
                    dateString -> stringToDate(dateString, "yyyy/MM"));

            // 2021-01-27
            pattenMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}$",
                    dateString -> stringToDate(dateString, "yyyy-MM-dd"));

            // 2021/02/03
            pattenMap.put("^\\d{4}/\\d{1,2}/\\d{1,2}$",
                    dateString -> stringToDate(dateString, "yyyy/MM/dd"));

            // 11/01/2021 07:46:33 Europe/London
            pattenMap.put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s?\\d{1,2}:\\d{1,2}:\\d{1,2}\\s?\\w+/\\w+$",
                    dateString -> {
                        // 截取字符串
                        dateString = dateString.substring(0, dateString.lastIndexOf(" "));
                        return stringToDate(dateString, "dd/MM/yyyy HH:mm:ss");
                    });

        }

        /**
         * 时间解析
         *
         * @param dateString
         * @return
         */
        public static Date format(String dateString) {
            Set<String> keySet = pattenMap.keySet();
            for (String key : keySet) {
                Pattern r = Pattern.compile(key);
                Matcher m = r.matcher(dateString);
                if (m.matches()) {
                    Function<String, Date> function = pattenMap.get(key);
                    return function.apply(dateString);
                }
            }
            throw new RuntimeException(String.format("未找到对应的时间格式：[%s]", dateString));
        }

        /**
         * 字符串转String
         *
         * @param dateString
         * @param format
         * @return
         */
        public static Date stringToDate(String dateString, String format) {
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            try {
                return dateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException(String.format("时间格式匹配错误：[%s]", dateString));
            }
        }

    }

}
