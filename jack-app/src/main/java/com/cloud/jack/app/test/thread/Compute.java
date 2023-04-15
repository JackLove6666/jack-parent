package com.cloud.jack.app.test.thread;

public class Compute {

    public static Integer compute(Integer i) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i * 5;
    }
}
