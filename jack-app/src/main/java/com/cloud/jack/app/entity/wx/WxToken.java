package com.cloud.jack.app.entity.wx;

import lombok.Data;

@Data
public class WxToken {

    private Integer errcode;

    private String errmsg;

    private String accessToken;

    private String expiresIn;

}
