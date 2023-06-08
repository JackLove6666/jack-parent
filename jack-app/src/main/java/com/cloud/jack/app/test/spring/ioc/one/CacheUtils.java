package com.cloud.jack.app.test.spring.ioc.one;

import java.util.HashMap;
import java.util.Map;

public class CacheUtils {


    public static  Map cacheMap = new HashMap<>();


    public static  void  put(String key,Object value){
        cacheMap.put(key,value);
    }


    public static Object get(String key){
        return cacheMap.get(key);
    }
}
