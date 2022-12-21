package com.cloud.jack.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.jack.gen.entity.Columns;
import com.cloud.jack.gen.entity.GenTableSchema;
import com.cloud.jack.gen.entity.Tables;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 代码生成规范-表结构
 *
 * @author Leo
 * @date 2021-06-13
 */
public interface GenTableSchemaService extends IService<GenTableSchema> {

    /**
     * 获取所有数据库
     *
     * @return
     */
    List<String> getAllDatabase();

    /**
     * 所有所有表
     *
     * @param tableCat
     * @return
     */
    List<Tables> getAllTables(String tableCat);

    List<Columns> getAllColumns(String tableCat, String tableName);

    /**
     * 生成行
     *
     * @param genTableSchema
     */
    void generateColumn(GenTableSchema genTableSchema);

    /**
     * 生成代码
     *
     * @param genTableSchema
     * @param response
     */
    void generateCode(GenTableSchema genTableSchema, HttpServletResponse response);

}
