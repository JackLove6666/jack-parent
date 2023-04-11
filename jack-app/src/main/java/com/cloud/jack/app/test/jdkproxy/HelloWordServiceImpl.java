package com.cloud.jack.app.test.jdkproxy;

public class HelloWordServiceImpl implements HelloWorldService{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void hello(String name) {
        System.out.println("hello!"+name);
    }
}
