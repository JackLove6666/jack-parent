package com.cloud.jack.core.config;

/**
 * 解决HTTP协议链式传递TOKEN问题 TODO 暂不开放
 *
 * @author Leo  implements RequestInterceptor
 */
//@Configuration
@Deprecated
public class FeignConfig {

//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //添加token
//        requestTemplate.header(JwtTokenUtils.AUTHORIZATION, request.getHeader(JwtTokenUtils.AUTHORIZATION));
//    }
}
