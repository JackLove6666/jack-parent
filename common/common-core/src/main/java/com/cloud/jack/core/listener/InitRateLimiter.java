package com.cloud.jack.core.listener;

import com.cloud.jack.core.abnormal.ServiceException;
import com.cloud.jack.core.annotation.ApiRateLimiter;
import com.cloud.jack.core.aspect.ApiLimitVo;
import com.cloud.jack.core.aspect.ApiRateLimiterAspect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

@Component
public class InitRateLimiter implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RestController.class);

        Set<String> keys = beanMap.keySet();
        for (String key : keys) {
            Class<?> clz = beanMap.get(key).getClass();
            String fullName = beanMap.get(key).getClass().getName();
            if (fullName.contains("EnhancerBySpringCGLIB") || fullName.contains("$$")) {
                fullName = fullName.substring(0, fullName.indexOf("$$"));
                try {
                    clz = Class.forName(fullName);
                } catch (ClassNotFoundException e) {
                    throw new ServiceException("获取类信息异常");
                }
            }
            Method[] methods = clz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ApiRateLimiter.class)) {
                    ApiRateLimiter annotation = method.getAnnotation(ApiRateLimiter.class);
                    String confKey = annotation.confKey();
                    int maxThreadCount = annotation.maxThreadCount();
                    ApiLimitVo apiLimitVo = new ApiLimitVo();
                    apiLimitVo.setConfKey(confKey);
                    apiLimitVo.setMaxThreadCount(maxThreadCount);
                    apiLimitVo.setValue(annotation.value());
                    apiLimitVo.setMaxWaitTime(annotation.maxWaitTime());
                    apiLimitVo.setSemaphore(new Semaphore(maxThreadCount));
                    ApiRateLimiterAspect.semaphore.put(confKey, apiLimitVo);

                }
            }
        }

//        beansWithAnnotation.forEach((k,v) ->{
//            Class<?> aClass = v.getClass();
//            Method[] methods = aClass.getDeclaredMethods();
//            for (Method method : methods) {
//                System.out.println(method.getName());
//                if("getSysUserPage".equals(method.getName())){
//                    System.out.println("+++++"+method.getName());
//                }
//                if(method.isAnnotationPresent(ApiRateLimiter.class)){
//                    ApiRateLimiter apiRateLimiter = method.getAnnotation(ApiRateLimiter.class);
//                    String confKey = apiRateLimiter.confKey();
//                    int maxThreadCount = apiRateLimiter.maxThreadCount();
//                    ApiLimitVo apiLimitVo = new ApiLimitVo();
//                    apiLimitVo.setConfKey(confKey);
//                    apiLimitVo.setValue(apiRateLimiter.value());
//                    apiLimitVo.setMaxThreadCount(maxThreadCount);
//                    apiLimitVo.setMaxWaitTime(apiRateLimiter.maxWaitTime());
//                    apiLimitVo.setSemaphore(new Semaphore(maxThreadCount));
//                    ApiRateLimiterAspect.semaphore.put(confKey,apiLimitVo);
//                }
//            }
//        });
    }
}
