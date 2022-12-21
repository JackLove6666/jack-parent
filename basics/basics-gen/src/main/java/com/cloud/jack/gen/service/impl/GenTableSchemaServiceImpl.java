package com.cloud.jack.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.jack.core.utils.CamelCaseUtil;
import com.cloud.jack.gen.entity.Columns;
import com.cloud.jack.gen.entity.GenColumnSchema;
import com.cloud.jack.gen.entity.GenTableSchema;
import com.cloud.jack.gen.entity.Tables;
import com.cloud.jack.gen.enums.JavaTypeConverter;
import com.cloud.jack.gen.mapper.GenTableSchemaMapper;
import com.cloud.jack.gen.service.GenColumnSchemaService;
import com.cloud.jack.gen.service.GenTableSchemaService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.EnumUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

/**
 * 代码生成规范-表结构
 *
 * @author Leo
 * @date 2021-06-13
 */
@Service("genTableSchemaService")
public class GenTableSchemaServiceImpl extends ServiceImpl<GenTableSchemaMapper, GenTableSchema> implements GenTableSchemaService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private GenColumnSchemaService genColumnSchemaService;

    @Override
    public List<String> getAllDatabase() {
        return baseMapper.getAllDatabase();
    }

    @Override
    public List<Tables> getAllTables(String tableCat) {
        return baseMapper.getAllTables(tableCat);
    }

    @Override
    public List<Columns> getAllColumns(String tableCat, String tableName) {
        return baseMapper.getAllColumns(tableCat, tableName);
    }

    @Override
    public void generateColumn(GenTableSchema genTableSchema) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            // 1.获取链接
            connection = dataSource.getConnection();
            // 2.获取元数据
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // 3.获取所有数据库列表
            resultSet = databaseMetaData.getColumns(genTableSchema.getTableCat(), null, genTableSchema.getTableName(), null);
            // 4.获取主键
            Set<String> primaryKeySet = getPrimaryKey(databaseMetaData, genTableSchema.getTableCat(), genTableSchema.getTableName());
            List<GenColumnSchema> columns = new ArrayList<>();
            // 5.读取资源
            while (resultSet.next()) {
                JavaTypeConverter converter = EnumUtils.getEnum(JavaTypeConverter.class, resultSet.getString("TYPE_NAME"));
                GenColumnSchema genColumnSchema = new GenColumnSchema();
                genColumnSchema.setParentId(genTableSchema.getId());
                genColumnSchema.setTableCat(resultSet.getString("TABLE_CAT"));
                genColumnSchema.setTableName(resultSet.getString("TABLE_NAME"));
                genColumnSchema.setColumnName(resultSet.getString("COLUMN_NAME"));
                if (converter != null) {
                    genColumnSchema.setTypeName(converter.getJavaType());
                }
                genColumnSchema.setNullable(resultSet.getBoolean("NULLABLE"));
                genColumnSchema.setRemarks(resultSet.getString("REMARKS"));
                genColumnSchema.setVarName(CamelCaseUtil.toCamelCase(resultSet.getString("COLUMN_NAME")));
                if (primaryKeySet.contains(resultSet.getString("COLUMN_NAME"))) {
                    genColumnSchema.setPrimaryKey(true);
                }
                genColumnSchema.setIsSearch(false);
                genColumnSchema.setIsExcel(0);
                genColumnSchema.setIsList(true);
                genColumnSchema.setIsEdit(true);
                genColumnSchema.setCreateTime(new Date());
                genColumnSchema.setUpdateTime(new Date());
                columns.add(genColumnSchema);
            }

            LambdaUpdateWrapper<GenColumnSchema> updateWrapper = new LambdaUpdateWrapper();
            updateWrapper.eq(GenColumnSchema::getParentId, genTableSchema.getId());
            genColumnSchemaService.remove(updateWrapper);
            genColumnSchemaService.saveBatch(columns);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void generateCode(GenTableSchema genTableSchema, HttpServletResponse response) {
        genTableSchema = getById(genTableSchema.getId());

        if (genTableSchema == null) {
            return;
        }

        LambdaQueryWrapper<GenColumnSchema> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GenColumnSchema::getParentId, genTableSchema.getId());
        List<GenColumnSchema> genColumnSchemas = genColumnSchemaService.list(wrapper);
        genTableSchema.setColumns(genColumnSchemas);

        if (CollectionUtils.isEmpty(genColumnSchemas)) {
            return;
        }

        //设置响应参数
        try {
            response.setContentType("application/zip");
            response.addHeader("Content-Transfer-Encoding", "binary");
            // URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode("代码", "UTF-8").replaceAll("\\+", "%20");
            response.addHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".zip");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //声明 压缩流对象、临时文件夹
        Path tempDirectory = null;
        OutputStream outputStream = null;
        ZipOutputStream zipOs = null;
        try {
            //创建临时文件夹（使用后删除）
            tempDirectory = Files.createTempDirectory(null);
            String dirName = tempDirectory.toString();

            // 生成代码
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setClassForTemplateLoading(GenTableSchema.class, "/template");
            Template entityTemplate = cfg.getTemplate("Entity.ftl", "UTF-8");
            entityTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + ".java")));

            Template mapperTemplate = cfg.getTemplate("Mapper.ftl", "UTF-8");
            mapperTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "Mapper.java")));

            Template mapperXmlTemplate = cfg.getTemplate("MapperXml.ftl", "UTF-8");
            mapperXmlTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "Mapper.xml")));

            Template serviceTemplate = cfg.getTemplate("Service.ftl", "UTF-8");
            serviceTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "Service.java")));

            Template serviceImplTemplate = cfg.getTemplate("ServiceImpl.ftl", "UTF-8");
            serviceImplTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "ServiceImpl.java")));

            Template controllerTemplate = cfg.getTemplate("Controller.ftl", "UTF-8");
            controllerTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "Controller.java")));

            Template apiTemplate = cfg.getTemplate("API.ftl", "UTF-8");
            apiTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "API.js")));

            Template controllerAPITemplate = cfg.getTemplate("ControllerAPI.ftl", "UTF-8");
            controllerAPITemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "API.java")));

            Template clientTemplate = cfg.getTemplate("Client.ftl", "UTF-8");
            clientTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + "Client.java")));

            Template vueTemplate = cfg.getTemplate("Vue.ftl", "UTF-8");
            vueTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + "index.vue")));

            Template sqlTemplate = cfg.getTemplate("Sql.ftl", "UTF-8");
            sqlTemplate.process(genTableSchema, new FileWriter(new File(dirName + "/" + genTableSchema.getClassName() + ".sql")));

            //获取 Response 对象的输出流,并用 ZipOutputStream 包装
            outputStream = response.getOutputStream();
            zipOs = new ZipOutputStream(outputStream);
            //将临时文件夹中所有 Excel 添加到 zip 压缩包中
            compressedFiles(zipOs, dirName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                if (zipOs != null) {
                    zipOs.finish();
                    zipOs.close();
                }
                //删除临时文件夹
                if (zipOs != null) {
                    tempDirectory.toFile().delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将临时文件夹中所有 Excel 添加到 zip 压缩包中
     *
     * @param out
     * @param dirName
     */
    private void compressedFiles(ZipOutputStream out, String dirName) {
        //取临时文件夹下所有 Excel 文件，添加到 zip 压缩包中
        File[] files = new File(dirName).listFiles();
        Arrays.stream(files).forEach(file -> {
            //将excel文件放入zip压缩包
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                out.putNextEntry(new ZipEntry(file.getName()));
                //设置压缩文件内的字符编码,读入需要下载的文件的内容，打包到zip文件
                out.setEncoding("UTF-8");
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Excel表格未压缩成功！");
            }
        });
    }

    /**
     * 获取主键
     *
     * @param databaseMetaData
     * @param catalog
     * @param table
     * @return
     * @throws Exception
     */
    private static Set<String> getPrimaryKey(DatabaseMetaData databaseMetaData, String catalog, String table) throws Exception {
        Set set = new HashSet();
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(catalog, null, table);
        while (resultSet.next()) {
            set.add(resultSet.getString("COLUMN_NAME"));
        }
        return set;
    }
}
