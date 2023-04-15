package com.cloud.jack.app.test.thread;

public class ThreadTest {

    static {

    }

    public static void main(String[] args) {
        //线程阻塞状态
        //waiting 等待状态
        //timed_waiting 超时等待状态
        //blocked 阻塞状态
        //runnable 可运行状态
        //terminated 终止状态
    }


    public void showThreadStatus() throws InterruptedException {
        //线程的生命周期
        //new
        //runnable
        //running
        //blocked
        //waiting
        new Object().wait();
        new Thread().join();
        //timed_waiting
        new Object().wait(1000);
        new Thread().join(1000);
        //terminated

    }
}
