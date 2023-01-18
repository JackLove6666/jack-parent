package com.cloud.jack.core.aspect;


import com.cloud.jack.core.abnormal.ServiceException;
import com.cloud.jack.core.abnormal.TrobDeniedException;
import com.cloud.jack.core.annotation.ApiRateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ApiRateLimiterAspect {


    public static Map<String,ApiLimitVo> semaphore = new ConcurrentHashMap();

    @Around(value = "@annotation(apiRateLimiter)" ) //调用一个具体方法前和调用后来完成一些具体的任务
    public Object around(ProceedingJoinPoint joinPoint, ApiRateLimiter apiRateLimiter){
        Object result = null;
        Class<?> aClass = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();
        String key = getKey(aClass, name);
        ApiLimitVo apiLimitVo = semaphore.get(key);

        Semaphore semaphore = apiLimitVo.getSemaphore();
        boolean b;
        try {
             b = semaphore.tryAcquire(apiLimitVo.getMaxWaitTime(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ServiceException("获取资源失败");
        }
        if(b){
            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
               if(throwable instanceof ServiceException){
                    throw new ServiceException(throwable.getMessage());
               }else {
                    throw new RuntimeException("调用失败");
               }
            }finally {
                semaphore.release();
            }
        }else {
            throw new TrobDeniedException("已经有"+apiLimitVo.getMaxThreadCount()+"个任务在处理"+apiLimitVo.getValue()+"，请稍后再试");
        }
        return result;
    }

    private String getKey(Class aClass,String methodName){
        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            if(declaredMethod.getName().equals(methodName)){
                if(declaredMethod.isAnnotationPresent(ApiRateLimiter.class)){
                    ApiRateLimiter annotation = declaredMethod.getAnnotation(ApiRateLimiter.class);
                    return annotation.confKey();
                }
            }
        }
        return null;
    }




}
