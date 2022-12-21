package ${packages!}.service.impl.${module!};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packages!}.entity.${module!}.${className};
import ${packages!}.mapper.${module!}.${className}Mapper;
import ${packages!}.service.${module!}.${className}Service;
import org.springframework.stereotype.Service;

/**
* ${remarks!}
*
* @author ${author!}
* @date ${date?string('yyyy-MM-dd')}
*/
@Service("${className?uncap_first}Service")
public class ${className}ServiceImpl extends ServiceImpl${"<"}${className}Mapper, ${className}${">"} implements ${className}Service {

}
