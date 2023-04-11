package com.cloud.jack.app.utils;

import com.cloud.jack.app.core.R;
import com.cloud.jack.app.entity.AttachEntity;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtil {


    /**
     * todo
     * @param threadPoolExecutor
     * @param threadUtilCallBack
     */
    public void doBusiness(ThreadPoolExecutor threadPoolExecutor,ThreadUtilCallBack threadUtilCallBack){
        Callable<AttachEntity> callable = ()->{
            AttachEntity attachEntity = new AttachEntity();
            return attachEntity;
        };
        Future<?> future = threadPoolExecutor.submit(callable);
    }


    /**
     * 创建可缓存、一个定时线程池
     * @param name
     * @param corePoolSize
     * @return
     */
    public static ThreadPoolExecutor createNamedExecutor(String name,Integer corePoolSize){
        return new ThreadPoolExecutor(corePoolSize,Integer.MAX_VALUE,60L, TimeUnit.SECONDS
                ,new LinkedBlockingQueue<>(),new NamedThreadFactory(name));
    }

    /**
     * 获取固定线程数线程池
     * @param name
     * @param size
     * @param queueSize
     * @param rejectedExecutionHandler
     * @return
     */

    public static ThreadPoolExecutor createFixExecutor(String name, Integer size, int queueSize, RejectedExecutionHandler rejectedExecutionHandler){
        return new ThreadPoolExecutor(size,size,60L,TimeUnit.SECONDS,new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory(name),
                rejectedExecutionHandler);
    }


    /**
     * 获取单线程线程池
     */
    public static ThreadPoolExecutor createSimpleExecutor(String name){
        return new ThreadPoolExecutor(1,1,60L,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),new NamedThreadFactory(name));
    }

    private static class NamedThreadFactory implements ThreadFactory {

        private final String name;
        private AtomicInteger i = new AtomicInteger(0);

        public NamedThreadFactory(String name) {
            this.name = name;
        }
        @Override
        public Thread newThread(Runnable r) {
            String format = String.format("%s-pool-%d", name,i.getAndIncrement());
            return new Thread(r, format);
        }
    }
}
