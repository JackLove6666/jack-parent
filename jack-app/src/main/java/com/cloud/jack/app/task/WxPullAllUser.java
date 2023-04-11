package com.cloud.jack.app.task;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.jack.app.config.WeiXinConfig;
import com.cloud.jack.app.entity.wx.*;
import com.cloud.jack.app.mapper.DepartmentMapper;
import com.cloud.jack.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WxPullAllUser {


    @Autowired
    private WeiXinConfig weiXinConfig;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

        @Transactional(rollbackFor = Exception.class)
        public void WxPullAllUser(){
            String appId = weiXinConfig.getAppId();
            String secret = weiXinConfig.getSecret();

            String getTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

            Map<String,Object> paramMap = new HashMap();
            paramMap.put("corpid",appId);
            paramMap.put("corpsecret",secret);
            String resultToken = HttpUtil.get(getTokenUrl, paramMap);

            WxToken wxToken = JSON.parseObject(resultToken, WxToken.class);

            System.out.println(wxToken);

            String getDeptUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+wxToken.getAccessToken();
            String resultDept = HttpUtil.get(getDeptUrl);
            DepartmentResponse departmentResponse = JSON.parseObject(resultDept, DepartmentResponse.class);


            List<Department> departmentList = departmentResponse.getDepartment();
            List<Integer> deptIds = departmentList.stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Integer, Department> departmentMap = departmentList.stream().collect(Collectors.toMap(item -> item.getId(), item -> item));
            List<Department> selectExistList = departmentMapper.selectList(new QueryWrapper<Department>().in("id", deptIds));
            if(CollUtil.isNotEmpty(selectExistList)){
                Map<Integer, Department> departmentExistMap = selectExistList.stream().collect(Collectors.toMap(item -> item.getId(), item -> item));
                departmentMap.forEach((k,v) -> {
                    if(departmentExistMap.containsKey(k)){
                        departmentMapper.updateById(v);
                    }else {
                        departmentMapper.insert(v);
                    }
                    String getUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+wxToken.getAccessToken()+"&department_id="+k;
                    String resultUser = HttpUtil.get(getUserUrl);
                    UserResponse userResponse = JSON.parseObject(resultUser, UserResponse.class);
                    List<User> userList = userResponse.getUserlist();
                    Map<String, User> userMap = userList.stream().collect(Collectors.toMap(item -> item.getUserid(), item -> item));
                    List<String> userIds = userList.stream().map(item -> item.getUserid()).collect(Collectors.toList());
                    if(CollUtil.isNotEmpty(userIds)){
                        List<User> selectExistUserList = userMapper.selectList(new QueryWrapper<User>().in("userid", userIds));
                        if(CollUtil.isNotEmpty(selectExistUserList)){
                            Map<String, User> userExistMap = selectExistUserList.stream().collect(Collectors.toMap(item -> item.getUserid(), item -> item));
                            userMap.forEach((userId,user) ->{
                                if(userExistMap.containsKey(userId)){
                                    User user1 = userExistMap.get(userId);
                                    user.setId(user1.getId());
                                    userMapper.updateById(user);
                                }else {
                                    userMapper.insert(user);
                                }
                            });
                        }else {
                            for (User user : userList) {
                                userMapper.insert(user);

                            }
                        }
                    }


                });
            }else {
                Map<String,User> userMap = new HashMap<String,User>();
                for (Department department : departmentList) {
                    departmentMapper.insert(department);
                    String getUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+wxToken.getAccessToken()+"&department_id="+department.getId();
                    String resultUser = HttpUtil.get(getUserUrl);
                    UserResponse userResponse = JSON.parseObject(resultUser, UserResponse.class);
                    List<User> userList = userResponse.getUserlist();
                    for (User user : userList) {
                        userMap.put(user.getUserid(),user);
                    }


                }
                userMap.forEach((userId,user) ->{
                    userMapper.insert(user);
                });
            }





//            String getUserListUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/list_id?access_token="+ wxToken.getAccessToken()+"&limit=10000";
//            String resultUserList = HttpUtil.get(getUserListUrl);
//
//            DepartmentUserResponse departmentUserResponse = JSON.parseObject(resultUserList, DepartmentUserResponse.class);
//            System.out.println(departmentUserResponse.getDept_user().size());


        }


        public void updateEmail(){
            String appId = weiXinConfig.getAppId();
            String secret = weiXinConfig.getSecret();

            String getTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

            Map<String,Object> paramMap = new HashMap();
            paramMap.put("corpid",appId);
            paramMap.put("corpsecret",secret);
            String resultToken = HttpUtil.get(getTokenUrl, paramMap);

            WxToken wxToken = JSON.parseObject(resultToken, WxToken.class);

            List<User> users = userMapper.selectList(new QueryWrapper<>());
            for (User user : users) {
                String userUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+wxToken.getAccessToken()+"&userid="+user.getUserid();
                String resultUser = HttpUtil.get(userUrl);
                User userResponse = JSON.parseObject(resultUser, User.class);
                user.setEmail(userResponse.getEmail());
                user.setMobile(userResponse.getMobile());
                user.setAlias(userResponse.getAlias());
                user.setPosition(userResponse.getPosition());
                user.setMain_department(userResponse.getMain_department());
                userMapper.updateById(user);
            }

        }
}
