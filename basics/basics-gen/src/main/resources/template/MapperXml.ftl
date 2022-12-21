${"<"}?xml version="1.0" encoding="UTF-8"?${">"}
${"<"}!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"${">"}

<mapper namespace="${packages!}.mapper.${module!}.${className}Mapper">

    <resultMap id="${className}Map" type="${packages!}.entity.${module!}.${className}">
    <#if columns??>
        <#list columns as column>
            <#if column.primaryKey??>
                <id property="${column.varName}" column="${column.columnName}"/>
                <#else>
                <result property="${column.varName}" column="${column.columnName}"/>
            </#if>
        </#list>
    </#if>
    </resultMap>
</mapper>
