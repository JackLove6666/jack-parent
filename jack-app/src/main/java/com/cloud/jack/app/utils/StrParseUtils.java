package com.cloud.jack.app.utils;

import cn.hutool.core.collection.CollUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StrParseUtils {

    /**
     * 解析上传名称为map
     */
    public static void parseUploadName(String fileName, String placeholder, Map<String, Object> map) {
        String fileNameNoSuffix = fileName.substring(0,fileName.lastIndexOf("."));
        String[] paramValueArray = fileNameNoSuffix.split("#");
        List<String> paramNameList = parsePlaceholder("#{", "}", placeholder);
        if (CollUtil.isEmpty(paramNameList) || paramNameList.size() != paramValueArray.length) {
            return;
        }
        for (int i = 0; i < paramNameList.size(); i++) {
            map.put(paramNameList.get(i), paramValueArray[i]);
        }
    }

    /**
     * 解析占位符，提取占位符参数名称
     *
     * @param openToken  开始占位符
     * @param closeToken 结束占位符
     * @param text       需要解析的内容
     * @return
     */
    public static List<String> parsePlaceholder(String openToken, String closeToken, String text) {
        List<String> paramNameList = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return paramNameList;
        }
        int start = text.indexOf(openToken);
        if (start == -1) {
            return paramNameList;
        }
        char[] src = text.toCharArray();
        int offset = 0;
        while (start > -1) {
            if (start > 0 && src[start - 1] == '\\') {
                offset = start + openToken.length();
            } else {
                offset = start + openToken.length();
                int end = text.indexOf(closeToken, offset);
                while (end > -1) {
                    if (end > offset && src[end - 1] == '\\') {
                        offset = end + closeToken.length();
                        end = text.indexOf(closeToken, offset);
                    } else {
                        paramNameList.add(text.substring(offset, end - closeToken.length() + 1));
                        break;
                    }
                }
                if (end == -1) {
                    offset = src.length;
                } else {
                    offset = end + closeToken.length();
                }
            }
            start = text.indexOf(openToken, offset);
        }
        return paramNameList;
    }
}
