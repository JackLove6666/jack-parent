package com.cloud.jack.app.task;


import cn.hutool.core.util.StrUtil;
import com.cloud.jack.app.utils.ThreadPoolUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class RemindEmail {

    @Autowired
    private SendEmailHandler sendEmailHandler;


    public void sendEmail(){
        ThreadPoolExecutor namedExecutor = ThreadPoolUtil.createNamedExecutor("remind", 16);
        Pair<Set<String>, Map<String, String>> resultSetMapPair = sendEmailHandler.sendEmailCommonHandler(namedExecutor, (user, contentMap) -> {


            System.out.println(namedExecutor);
            System.out.println("user: " + user);
            System.out.println("contentMap: " + contentMap);
            if(StrUtil.equals(user.getAlias(),"Jack Jong")){
                throw new RuntimeException("排除你了");
            }
        });

        System.out.println("resultSetMapPair="+resultSetMapPair);

    }




}
