package com.cloud.jack.app.task;

import com.cloud.jack.app.entity.wx.User;


import java.util.Map;

public interface SendEmailHandlerCallBack {



    void doBusiness(User user, Map<String,String> contentMap);

}
