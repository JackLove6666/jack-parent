package ${packages!}.entity.${module!};

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* ${remarks!}
*
* @author ${author!}
* @date ${date?string('yyyy-MM-dd')}
*/
@Data
@TableName("${tableName}")
@ApiModel(value = "${remarks}")
public class ${className} extends Model<${className}> {
<#if columns??>
    <#list columns as column>

    @ApiModelProperty(value = "${column.remarks}")
    <#if column.primaryKey??>
    @TableId
    </#if>
    private ${column.typeName} ${column.varName};
    </#list>
</#if>
}
