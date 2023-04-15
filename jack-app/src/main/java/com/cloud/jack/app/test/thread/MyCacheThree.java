package com.cloud.jack.app.test.thread;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCacheThree {

    private static Map<Object, Future<Object>> map = new ConcurrentHashMap<>();

    public static AtomicInteger count = new AtomicInteger(0);

    public static Object getResult(MyBusiness myBusiness, Object obj) {
        Object result = null;
        Future<Object> future = map.get(obj);
        if (future == null) {
            FutureTask futureTask = new FutureTask(() -> myBusiness.doBusiness(obj));
            future = futureTask;
            map.put(obj, futureTask);
            futureTask.run();
        } else {
            count.getAndIncrement();
        }
        try {
            // zuse
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(String.format("输入%s,输出%s", obj, result));
        return result;
    }
}
