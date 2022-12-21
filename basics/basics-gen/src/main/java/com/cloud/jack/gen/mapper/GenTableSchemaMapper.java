package com.cloud.jack.gen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.jack.gen.entity.Columns;
import com.cloud.jack.gen.entity.GenTableSchema;
import com.cloud.jack.gen.entity.Tables;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 代码生成规范-表结构
 *
 * @author Leo
 * @date 2021-06-13
 */
@Mapper
public interface GenTableSchemaMapper extends BaseMapper<GenTableSchema> {

    List<String> getAllDatabase();

    List<Tables> getAllTables(@Param("tableCat") String tableCat);

    List<Columns> getAllColumns(@Param("tableCat") String tableCat, @Param("tableName") String tableName);
}
