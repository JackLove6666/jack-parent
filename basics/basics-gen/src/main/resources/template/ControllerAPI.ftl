import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ${packages!}.entity.${module!}.${className};
import ${packages!}.service.${module!}.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* ${remarks!}API
*
* @author ${author!}
* @date ${date?string('yyyy-MM-dd')}
*/
@RestController
@RequestMapping("/api/${className?uncap_first}")
public class ${className}API {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @PostMapping("/list")
    public List<${className}> getList(@RequestBody ${className} ${className?uncap_first}) {
        return ${className?uncap_first}Service.list(Wrappers.query(${className?uncap_first}));
    }

    @GetMapping("/{id}")
    public ${className} getById(@PathVariable("id") Long id) {
        return ${className?uncap_first}Service.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public Boolean save(@RequestBody ${className} ${className?uncap_first}) {
        return ${className?uncap_first}Service.save(${className?uncap_first});
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Boolean updateById(@RequestBody ${className} ${className?uncap_first}) {
        return ${className?uncap_first}Service.updateById(${className?uncap_first});
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    public Boolean removeById(@PathVariable Long id) {
        return ${className?uncap_first}Service.removeById(id);
    }

}
