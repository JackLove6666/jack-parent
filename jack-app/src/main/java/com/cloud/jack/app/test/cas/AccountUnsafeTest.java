package com.cloud.jack.app.test.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class AccountUnsafeTest {

    public static void main(String[] args) {
        // 100个线程同时取钱，每次取100，取100次，最后余额应该是0
        AccountUnsafe account = new AccountUnsafe(10000);
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
