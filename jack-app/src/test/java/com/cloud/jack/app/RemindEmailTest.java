package com.cloud.jack.app;


import com.cloud.jack.app.task.RemindEmail;
import com.cloud.jack.app.task.WxPullAllUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemindEmailTest {


    @Autowired
    private RemindEmail remindEmail;

    @Test
    public void test(){
        remindEmail.sendEmail();
    }




}
