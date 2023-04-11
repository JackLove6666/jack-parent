package com.cloud.jack.app.test;

import com.cloud.jack.app.utils.ThreadPoolUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadUtils {

    public static void handleThread(){
        //写一个并发处理类要求
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);

        for (Integer integer : integerList) {
            System.out.println(integer);
        }

    }


    public static void main(String[] args) {
        //写一个并发处理类要求
        // 1.线程池
        ThreadPoolExecutor namedExecutor = ThreadPoolUtil.createNamedExecutor("test", 10);
        namedExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });
        namedExecutor.shutdown();
        // 2.线程安全

        // 3.线程执行完毕后的回调


        //输出今天日期并格式化"yyyy-MM-dd"
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(sdf.format(new Date()));



    }
}
