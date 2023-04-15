package com.cloud.jack.app.test;

import com.cloud.jack.app.test.cas.AccountCAS;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;

public class CompletionServiceTest {

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

    public CompletionServiceTest(Integer balance) {
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
        //future cancel 怎么线程里的关闭流

        CompletionServiceTest account = new CompletionServiceTest(10000);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < 1000; i++) {
            Future submit = completionService.submit(() -> {
                account.withdraw(10);
                return null;
            });
        }

        for (int i = 0; i < 1000; i++) {
            try {
                Object o = completionService.take().get();
                System.out.println("o"+o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
