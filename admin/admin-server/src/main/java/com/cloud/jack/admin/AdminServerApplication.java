package com.cloud.jack.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.cloud"})
@MapperScan("com.cloud.jack.admin.mapper")
public class AdminServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class,args);
    }
}
