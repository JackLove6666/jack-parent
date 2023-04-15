package com.cloud.jack.app.test.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCacheFour {

    private static Map<Object, Future<Object>> map = new ConcurrentHashMap<>();

    public static AtomicInteger count = new AtomicInteger(0);

    //
    public static Object getResult(MyBusiness myBusiness, Object obj) {
        Object result = null;
        Future<Object> future = map.get(obj);
        if (future == null) {
            FutureTask futureTask = new FutureTask(() -> myBusiness.doBusiness(obj));
            // 线程安全
            future = map.putIfAbsent(obj, futureTask);
            if (future == null) {
                future = futureTask;
                futureTask.run();
            } else {
                count.getAndIncrement();
            }
        } else {
            count.getAndIncrement();
        }
        try {
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(String.format("输入%s,输出%s", obj, result));
        return result;
    }

    public static void removeKey(Object object){
        map.remove(object);
    }



}
