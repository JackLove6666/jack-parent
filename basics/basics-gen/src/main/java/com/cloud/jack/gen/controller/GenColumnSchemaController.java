package com.cloud.jack.gen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.jack.core.R;
import com.cloud.jack.gen.entity.GenColumnSchema;
import com.cloud.jack.gen.service.GenColumnSchemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
* 代码生成规范-行结构
*
* @author Leo
* @date 2021-06-13
*/
@RestController
@RequestMapping("/genColumnSchema")
@Api(value = "genColumnSchema", tags = "代码生成规范-行结构模块")
public class GenColumnSchemaController {

    @Autowired
    private GenColumnSchemaService genColumnSchemaService;

    /**
    * 分页查询
    *
    * @param page
    * @param genColumnSchema
    * @return
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public R getPage(@ApiParam(name = "page", value = "分页信息", required = true) Page page,
                     @ApiParam(name = "genColumnSchema") GenColumnSchema genColumnSchema) {
        return new R<>(genColumnSchemaService.page(page, Wrappers.query(genColumnSchema)));
    }


    /**
    * 通过id查询代码生成规范-行结构
    *
    * @param id id
    * @return R
    */
    @ApiOperation(value = "通过id查询代码生成规范-行结构")
    @GetMapping("/{id}")
    public R getById(@ApiParam(name = "id", required = true) @PathVariable("id") Long id) {
        return new R<>(genColumnSchemaService.getById(id));
    }

    /**
    * 新增代码生成规范-行结构
    *
    * @param genColumnSchema
    * @return R
    */
    @ApiOperation(value = "新增代码生成规范-行结构")
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public R save(@ApiParam(name = "sysDict", required = true) @RequestBody GenColumnSchema genColumnSchema) {
        return new R<>(genColumnSchemaService.save(genColumnSchema));
    }

    /**
    * 修改代码生成规范-行结构
    *
    * @param genColumnSchema
    * @return R
    */
    @ApiOperation(value = "修改代码生成规范-行结构")
    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public R updateById(@ApiParam(name = "sysDict", required = true) @RequestBody GenColumnSchema genColumnSchema) {
        return new R<>(genColumnSchemaService.updateById(genColumnSchema));
    }

    /**
    * 通过id删除代码生成规范-行结构
    *
    * @param id id
    * @return R
    */
    @ApiOperation(value = "删除代码生成规范-行结构")
    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public R removeById(@ApiParam(name = "id", required = true) @PathVariable Long id) {
        return new R<>(genColumnSchemaService.removeById(id));
    }

}
