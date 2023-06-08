package com.cloud.jack.app;


import com.cloud.jack.app.test.spring.ioc.one.CustomizedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomizedServiceTest {

    @Autowired
    private CustomizedService customizedService;

    @Test
    public void test(){
        customizedService.doSomething();
    }
}
