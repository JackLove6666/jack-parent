package com.cloud.jack.gen.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.jack.core.R;
import com.cloud.jack.gen.entity.GenTableSchema;
import com.cloud.jack.gen.entity.Tables;
import com.cloud.jack.gen.service.GenTableSchemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 代码生成规范-表结构
 *
 * @author Leo
 * @date 2021-06-13
 */
@RestController
@RequestMapping("/genTableSchema")
@Api(value = "genTableSchema", tags = "代码生成规范-表结构模块")
public class GenTableSchemaController {

    @Autowired
    private GenTableSchemaService genTableSchemaService;

    /**
     * 分页查询
     *
     * @param page
     * @param genTableSchema
     * @return
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public R<Page<GenTableSchema>> getPage(@ApiParam(name = "page", value = "分页信息", required = true) Page page,
                                           @ApiParam(name = "genTableSchema") GenTableSchema genTableSchema) {
        return R.ok(genTableSchemaService.page(page, Wrappers.query(genTableSchema)));
    }


    /**
     * 通过id查询用户管理
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询代码生成规范-表结构")
    @GetMapping("/{id}")
    public R<GenTableSchema> getById(@ApiParam(name = "id", required = true) @PathVariable("id") Long id) {
        return R.ok(genTableSchemaService.getById(id));
    }

    /**
     * 新增用户管理
     *
     * @param genTableSchema
     * @return R
     */
    @ApiOperation(value = "新增代码生成规范-表结构")
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public R save(@ApiParam(name = "sysDict", required = true) @RequestBody GenTableSchema genTableSchema) {
        LambdaQueryWrapper<GenTableSchema> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GenTableSchema::getTableCat, genTableSchema.getTableCat());
        wrapper.eq(GenTableSchema::getTableName, genTableSchema.getTableName());
        List<GenTableSchema> list = genTableSchemaService.list(wrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return R.ok("已经生成表结构");
        }
        return R.ok(genTableSchemaService.save(genTableSchema));
    }

    /**
     * 修改用户管理
     *
     * @param genTableSchema
     * @return R
     */
    @ApiOperation(value = "修改代码生成规范-表结构")
    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> updateById(@ApiParam(name = "sysDict", required = true) @RequestBody GenTableSchema genTableSchema) {
        return R.ok(genTableSchemaService.updateById(genTableSchema));
    }

    /**
     * 通过id删除用户管理
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "删除代码生成规范-表结构")
    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> removeById(@ApiParam(name = "id", required = true) @PathVariable Long id) {
        return R.ok(genTableSchemaService.removeById(id));
    }

    @ApiOperation(value = "获取所有数据库")
    @GetMapping("/getAllDatabase")
    public R<List<String>> getAllDatabase() {
        return R.ok(genTableSchemaService.getAllDatabase());
    }

    @ApiOperation(value = "获取所有表")
    @GetMapping("/getAllTables")
    public R<List<Tables>> getAllTables(@ApiParam(name = "tableCat", required = true) @RequestParam(value = "tableCat") String tableCat) {
        return R.ok(genTableSchemaService.getAllTables(tableCat));
    }

    @ApiOperation(value = "获取所有字段")
    @GetMapping("/getAllColumns")
    public R<List<Tables>> getAllColumns(@ApiParam(name = "tableCat", required = true) @RequestParam(value = "tableCat") String tableCat,
                                         @ApiParam(name = "tableName", required = true) @RequestParam(value = "tableName")  String tableName) {
        return R.ok(genTableSchemaService.getAllColumns(tableCat, tableName));
    }

    @ApiOperation(value = "生成字段")
    @PostMapping("/generateColumn")
    public R generateColumn(@ApiParam(name = "genTableSchema", required = true) @RequestBody GenTableSchema genTableSchema) {
        genTableSchemaService.generateColumn(genTableSchema);
        return R.ok();
    }

    @ApiOperation(value = "生成代码")
    @PostMapping("/generateCode")
    public R generateCode(@ApiParam(name = "genTableSchema", required = true) @RequestBody GenTableSchema genTableSchema,
                          HttpServletResponse response) {
        genTableSchemaService.generateCode(genTableSchema, response);
        return R.ok();
    }

}
