package com.cloud.jack.gen.enums;


/**
 * @author ML
 * @date
 */

public enum JavaTypeConverter {

    /**
     * 数据库类型、JAVA类型、JAVA包
     */
    VARCHAR("VARCHAR", "String"),
    CHAR("CHAR", "String"),
    BLOB("BLOB", "byte[]"),
    TEXT("TEXT", "String"),
    INTEGER("VARCHAR", "Long"),
    INT("INT", "Integer"),
    TINYINT("TINYINT", "Boolean"),
    SMALLINT("SMALLINT", "Integer"),
    MEDIUMINT("MEDIUMINT", "Integer"),
    BIT("BIT", "Boolean"),
    BIGINT("BIGINT", "Long"),
    FLOAT("FLOAT", "Float"),
    DOUBLE("DOUBLE", "Double"),
    DECIMAL("DECIMAL", "BigDecimal"),
    DATE("DATE", "Date"),
    TIME("TIME", "Date"),
    DATETIME("DATETIME", "Date"),
    TIMESTAMP("TIMESTAMP", "Date");

    private String type;

    private String javaType;

    JavaTypeConverter(String type, String javaType) {
        this.type = type;
        this.javaType = javaType;
    }

    public String getType() {
        return type;
    }

    public String getJavaType() {
        return javaType;
    }

}
