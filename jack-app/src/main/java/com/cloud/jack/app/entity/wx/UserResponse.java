package com.cloud.jack.app.entity.wx;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Integer errcode;

    private String errmsg;

    private List<User> userlist;

}
