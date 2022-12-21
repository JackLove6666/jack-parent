package com.cloud.jack.core.utils;

/**
 * 驼峰工具类
 *
 * @author Leo
 */
public class CamelCaseUtil {

    private static final char SEPARATOR = '_';

    /**
     * 驼峰组合转小写字母下划线形式
     *
     * @param str
     * @return
     */
    public static String toUnderlineCase(String str) {
        if (null == str) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            boolean nextUpperCase = true;
            if (i < (str.length() - 1)) {
                nextUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }
            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 下划线形式组合的字符串转小驼峰
     */
    public static String toCamelCase(String str) {
        if (str == null) {
            return null;
        }
        str = str.toLowerCase();
        StringBuilder sb = new StringBuilder(str.length());
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线形式组合的字符串转大驼峰
     */
    public static String toCapitalizeCamelCase(String str) {
        if (null == str) {
            return null;
        }
        str = toCamelCase(str);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String toCapture(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

}

