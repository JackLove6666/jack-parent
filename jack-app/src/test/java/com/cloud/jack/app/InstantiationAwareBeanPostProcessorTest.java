package com.cloud.jack.app;


import com.cloud.jack.app.test.spring.ioc.two.BeanA;
import com.cloud.jack.app.test.spring.ioc.two.BeanAImpl;
import com.cloud.jack.app.test.spring.ioc.two.BeanB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstantiationAwareBeanPostProcessorTest {

    @Test
    public void Test(){
        BeanA beanA = new BeanAImpl();
        beanA.methodA1();
        BeanB beanB = new BeanB();
        beanB.methodB1();
    }
}
