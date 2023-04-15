package com.cloud.jack.app.test.lock;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {




    public static void readReadMode() throws InterruptedException {
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();

        Thread t1 = new Thread(() -> {
            readLock.lock();
            try {
                System.out.println("t1 readLock lock"+new Date());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            readLock.lock();
            try {
                System.out.println("t2 readLock lock"+new Date());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public static void readWriteMode() throws InterruptedException {
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();

        Thread t1 = new Thread(() -> {
            readLock.lock();
            try {
                System.out.println("t1 readLock lock"+new Date());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            writeLock.lock();
            try {
                Thread.sleep(1000);
                System.out.println("t2 writeLock lock"+new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {
//        readReadMode();
        readWriteMode();
    }
}
