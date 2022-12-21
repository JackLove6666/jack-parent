import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packages!}.core.R;
import ${packages!}.entity.${module!}.${className};
import ${packages!}.service.${module!}.${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* ${remarks!}
*
* @author ${author!}
* @date ${date?string('yyyy-MM-dd')}
*/
@RestController
@RequestMapping("/${className?uncap_first}")
@Api(value = "${className?uncap_first}", tags = "${remarks}")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public R<Page<${className}>> getPage(@ApiParam(name = "page", value = "分页信息", required = true) Page page,
    @ApiParam(name = "${className?uncap_first}") ${className} ${className?uncap_first}) {
        return R.ok(${className?uncap_first}Service.page(page, Wrappers.query(${className?uncap_first})));
    }

    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public R<List<${className}>> getList(@ApiParam(name = "${className?uncap_first}", required = true) @RequestBody ${className} ${className?uncap_first}) {
        return R.ok(${className?uncap_first}Service.list(Wrappers.query(${className?uncap_first})));
    }

    @ApiOperation(value = "通过id查询${remarks}")
    @GetMapping("/{id}")
    public R<${className}> getById(@ApiParam(name = "id", required = true) @PathVariable("id") Long id) {
        return R.ok(${className?uncap_first}Service.getById(id));
    }

    @ApiOperation(value = "新增${remarks}")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public R<Boolean> save(@ApiParam(name = "sysDict", required = true) @RequestBody ${className} ${className?uncap_first}) {
        return R.ok(${className?uncap_first}Service.save(${className?uncap_first}));
    }

    @ApiOperation(value = "修改${remarks}")
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public R<Boolean> updateById(@ApiParam(name = "sysDict", required = true) @RequestBody ${className} ${className?uncap_first}) {
        return R.ok(${className?uncap_first}Service.updateById(${className?uncap_first}));
    }

    @ApiOperation(value = "删除${remarks}")
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/{ids}")
    public R<Boolean> removeById(@ApiParam(name = "ids", required = true) @PathVariable List<Long> ids) {
        return R.ok(${className?uncap_first}Service.removeByIds(ids));
    }

}
