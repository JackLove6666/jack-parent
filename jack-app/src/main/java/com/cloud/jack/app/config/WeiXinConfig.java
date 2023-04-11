package com.cloud.jack.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WeiXinConfig {


    @Value("${weiXin.appId}")
    private String appId;

    @Value("${weiXin.secret}")
    private String secret;

    @Value("${weiXin.agentId}")
    private Integer agentId;
}
