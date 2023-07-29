package com.cloud.jack.app.test.juc;

import com.cloud.jack.app.common.R;

public class VolatileTest2 {


    int num = 0;

    boolean ready = false;

    public void actor1(R r) {
        num = 2;
        ready = true;
    }

    public void actor2(R r) {
        if (ready) {
            r.setData(num + num);
        }else {
           r.setData(1);
        }
    }

    public static void main(String[] args) {
        VolatileTest2 volatileTest2 = new VolatileTest2();
        R r = new R();
        new Thread(() -> {
            volatileTest2.actor1(r);
        }).start();
        new Thread(() -> {
            volatileTest2.actor2(r);
        }).start();
        System.out.println(r.getData());
    }
}
