package com.cloud.jack.app.test.thread;

import cn.hutool.core.util.RandomUtil;

import java.util.List;
import java.util.concurrent.*;

public class MyTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CompletionService service = new ExecutorCompletionService(executorService);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int param = RandomUtil.randomInt(5);
            service.submit(() ->
                    MyCacheFour.getResult(
                            obj -> Compute.compute(Integer.parseInt(obj.toString())), param)
            );
        }
        for (int i = 0; i < 1000; i++) {
            try {
                service.take().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        executorService.shutdown();
//        try {
//            executorService.awaitTermination(5,TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("取缓存次数:"+MyCacheFour.count);
        System.out.println("总耗时:"+(endTime-startTime));
    }
}
