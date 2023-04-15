package com.cloud.jack.app.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Exchanger;
@Slf4j
public class ExchangerTest {

    public static void main(String[] args) throws InterruptedException {
        //1.创建一个交换器
        Exchanger<String> exchanger = new Exchanger<>();
        //2.创建两个线程
        Thread boy = new Thread(() -> {
            log.info("你开始准备礼物~~~~~~~~~~~~");
            try {
                //3.线程A将a的值传递给线程B
                // 模拟准备礼物时间
                Thread.sleep(5000);
                String gift = "IPhone 14";
                log.info("你送了礼物: {}",  gift);
                String recGift = exchanger.exchange(gift);
                log.info("你收到了礼物: {}",  recGift);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread girl = new Thread(() -> {
            log.info("女朋友开始准备礼物~~~~~~~~~~~~");
            try {
                //4.线程B将b的值传递给线程A
                // 模拟准备礼物时间
                Thread.sleep(6000);
                String gift = "一个吻";
                log.info("女朋友送了礼物: {}",  gift);
                String recGift = exchanger.exchange(gift);
                log.info("女朋友收到了礼物: {}",  recGift);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        boy.start();
        girl.start();
        boy.join();
        girl.join();
        log.info("join等待线程结束");
    }
}
