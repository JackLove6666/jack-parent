import ${packages!}.entity.${module!}.${className};
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "luban-admin-server", path = "/api/${className?uncap_first}", contextId = "${className}Client")
public interface ${className}Client {

    /**
    * 通过ID查询
    *
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    ${className} getById(@PathVariable("id") Long id);

    /**
    * 列表查询
    *
    * @param ${className?uncap_first}
    * @return
    */
    @PostMapping("/list")
    List<${className}> getList(@RequestBody ${className} ${className?uncap_first});

    /**
    * 新增
    *
    * @param ${className?uncap_first}
    * @return
    */
    @PostMapping()
    boolean save(@RequestBody ${className} ${className?uncap_first});

    /**
    * 修改
    *
    * @param ${className?uncap_first}
    * @return
    */
    @PutMapping()
    boolean updateById(@RequestBody ${className} ${className?uncap_first});

    /**
    * 删除
    *
    * @param id
    * @return
    */
    @DeleteMapping("{id}")
    boolean removeById(@PathVariable Long id);

}
