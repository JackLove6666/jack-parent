package com.cloud.jack.app.test.juc;

public class VolatileTest {

    static boolean running = true;
    public static void main(String[] args) {

        new Thread(() -> {
            int i = 0;
            while (running) {
                i++;
            }
           //这行代码会执行吗？
            System.out.println("线程结束");
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }
}
