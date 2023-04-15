package com.cloud.jack.app.test.cas;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class AccountCAS {


    //余额
    private volatile int balance;
    //Unsafe对象
    private static final Unsafe unsafe;

    //balance在AccountCAS中的偏移量
    private static final long balanceOffset;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            balanceOffset = unsafe.objectFieldOffset(AccountCAS.class.getDeclaredField("balance"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public AccountCAS(Integer balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(Integer amount) {
        //自旋

        while (true) {
            int prev = balance;
            int next = prev - amount;
            if (unsafe.compareAndSwapInt(this, balanceOffset, prev, next)) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        // 100个线程同时取钱，每次取100，取100次，最后余额应该是0
        AccountCAS account = new AccountCAS(10000);
        List<Thread> ts = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(() -> {
                    account.withdraw(10);
            });
            ts.add(t);
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        // 余额不为0，说明有线程安全问题
        log.info("余额：{}，花费时间：{}", account.getBalance(), (end - start) / 1000_000+"ms");
    }
}
