package com.cloud.jack.app.test.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicIntegerTest {


    private static AtomicInteger atomicInteger  = new AtomicInteger(0);

    //解决ABA问题的原子类
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0,0);
}
