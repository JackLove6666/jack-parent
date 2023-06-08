package com.cloud.jack.app.test;

/**
 * 使用场景：线程池的创建和销毁操作通常比较耗时，因此使用单例模式可以实现全局仅有一个线程池对象实例，
 * 避免了重复创建和销毁造成的性能问题，同时也可以保证线程池在并发情况下的线程安全性。
 */
public class Singleton {


    private volatile static Singleton singleton;

    private Singleton(){
        System.out.println("我只允许单例");
    }

    public Singleton getSingleton() {
        if(singleton == null){
           synchronized (Singleton.class){
               if(singleton == null){
                   singleton = new Singleton();
               }
           }

        }
        return singleton;
    }


}
