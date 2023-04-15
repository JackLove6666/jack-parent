package com.cloud.jack.app.test.thread;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCacheTwo {

    private static Map<Object, Object> map = new ConcurrentHashMap<>();

    public static AtomicInteger count = new AtomicInteger(0);

    public static Object getResult(MyBusiness myBusiness, Object obj) {
        Object result;
        if (map.containsKey(obj)) {
            result = map.get(obj);
            count.getAndIncrement();
        } else {
            result = myBusiness.doBusiness(obj);
            map.put(obj, result);
        }
        System.out.println(String.format("输入%s,输出%s", obj, result));
        return result;
    }
}
