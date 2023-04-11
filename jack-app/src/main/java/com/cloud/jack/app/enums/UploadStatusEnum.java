package com.cloud.jack.app.enums;

import java.util.ArrayList;
import java.util.List;

public enum UploadStatusEnum {
    UPLOADED("UPLOADED", "已上传"),
    PARSING("PARSING", "解析中"),
    PARSE_SUCCESS("PARSE_SUCCESS", "解析成功"),
    PARSE_FAIL("PARSE_FAIL", "解析失败");

    UploadStatusEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    private String key;

    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据key的集合获取name的集合
     *
     * @param keyList
     * @return
     */
    public static List<String> getNamesBykeys(List<String> keyList) {
        List<String> nameList = new ArrayList<>();
        UploadStatusEnum[] values = UploadStatusEnum.values();
        for (UploadStatusEnum value : values) {
            if (keyList.contains(value.key)) {
                nameList.add(value.name);
            }
        }
        return nameList;
    }

    /**
     * @param key
     * @return
     */
    public static String getNameByKey(String key) {
        UploadStatusEnum[] values = UploadStatusEnum.values();
        for (UploadStatusEnum value : values) {
            if (value.getKey().equalsIgnoreCase(key)) {
                return value.getName();
            }
        }
        return null;
    }
}