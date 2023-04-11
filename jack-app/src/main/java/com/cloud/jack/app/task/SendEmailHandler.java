package com.cloud.jack.app.task;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.jack.app.entity.wx.User;
import com.cloud.jack.app.mapper.UserMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Component
public class SendEmailHandler {


    @Autowired
    private UserMapper userMapper;



    public Pair<Set<String>, Map<String, String>> sendEmailCommonHandler(ThreadPoolExecutor executor,SendEmailHandlerCallBack sendEmailHandlerCallBack){

        Set<String> successSet = new HashSet<>();
        Map<String, String> failMap = new HashMap<>();

        List<User> users = userMapper.selectList(new QueryWrapper<User>());
        System.out.println(users);
        System.out.println("============");
        Map<String, String> userMap = users.stream().filter(item -> ObjectUtil.isNotNull(item))
                .filter(item ->StrUtil.isNotEmpty(item.getUserid()) && StrUtil.isNotEmpty(item.getEmail())).
                collect(Collectors.toMap(item -> item.getUserid(), item -> item.getEmail()));
        Map<String, Pair<User, Map<String,String>>> userIdMap = new HashMap();
        users.forEach(item -> {
            if (userMap.containsKey(item.getUserid())) {
                userIdMap.put(item.getUserid(), Pair.of(item, userMap));
            }
        });
        CountDownLatch countDownLatch = new CountDownLatch(userIdMap.size());

        userIdMap.forEach((k,v) ->{
                executor.execute(() ->{
                        try {
                            sendEmailHandlerCallBack.doBusiness(v.getLeft(),v.getRight());
                            successSet.add(k);
                        }catch (Exception e){
                            e.printStackTrace();
                            failMap.put(k,v.getRight().get(k));
                        }finally {
                            countDownLatch.countDown();
                        }
                });
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("结束了!!!");
        executor.shutdown();
        return Pair.of(successSet,failMap);

    }
}
