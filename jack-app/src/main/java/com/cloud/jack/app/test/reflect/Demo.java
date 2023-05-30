package com.cloud.jack.app.test.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Student student = new Student();
        Class<? extends Student> studentClass = student.getClass();

        Constructor<? extends Student> constructor = studentClass.getConstructor();
        Student newInstance = constructor.newInstance();
        newInstance.setSname("jack");
        System.out.println(newInstance.getSname());
        //一个类中可以有多个构造方法，通过构造方法的参数类型来区分
        Constructor<? extends Student> constructor1 = studentClass.getConstructor(String.class);
        Student newInstance1 = constructor1.newInstance("1");
        System.out.println(newInstance1);

        Constructor<? extends Student> constructor2 = studentClass.getConstructor(String.class, String.class);
        Student newInstance2 = constructor2.newInstance("2", "jack");
        System.out.println(newInstance2);

        //私有的构造方法
        Constructor<? extends Student> constructor3 = studentClass.getDeclaredConstructor(Integer.class);
        constructor3.setAccessible(true);
        Student newInstance3 = constructor3.newInstance(5);
        System.out.println(newInstance3);

        Method hello = studentClass.getMethod("hello");
        newInstance3.setSname("jack5");
        hello.invoke(newInstance3);

        Method hello1 = studentClass.getMethod("hello", String.class);
        hello1.invoke(newInstance3,"jack6");

        Method add = studentClass.getDeclaredMethod("add", Integer.class, Integer.class);
        add.setAccessible(true);
        Integer invoke = (Integer) add.invoke(newInstance3, 1, 2);
        System.out.println("add 1,2 = " + invoke);

        Field sname = studentClass.getDeclaredField("sname");
        sname.setAccessible(true);
        sname.set(newInstance3,"jack7");
        System.out.println(newInstance3);




    }
}
