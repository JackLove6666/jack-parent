package com.cloud.jack.app.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量类基于AQS的共享锁实现，有公平锁和非公平锁两个版本，
 * 它用来限制能同时访问共享资源的线程上限，典型的应用场景是可以用来保护有限的公共资源，比如数据库连接等
 */
@Slf4j
public class SemaphoreTest {


    public static void main(String[] args) {
        //1.创建一个Semaphore对象，初始化许可证数量为1
        Semaphore semaphore = new Semaphore(2);
        //2.10个线程同时访问
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    //3.获取许可证
                    semaphore.acquire();
                    log.debug(Thread.currentThread().getName() + "获取到许可证");
                    //4.模拟业务逻辑
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //5.释放许可证
                    semaphore.release();
                    log.debug(Thread.currentThread().getName() + "释放到许可证");
                }
            }).start();
        }
    }
}
