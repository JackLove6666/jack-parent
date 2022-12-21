package com.cloud.jack.core.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.cloud.jack.core.CurrentUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UserUtils {

    public static CurrentUser getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String user = URLDecoder.decode(request.getHeader("USER"), StandardCharsets.UTF_8.toString());
            JSONObject jsonObject = new JSONObject(user);
            return CurrentUser.builder()
                    .id(jsonObject.getStr("id"))
                    .username(jsonObject.getStr("user_name"))
                    .account(jsonObject.getStr("account"))
                    .enabled(jsonObject.getBool("enabled"))
                    .roles(Convert.toList(String.class, jsonObject.get("authorities"))).build();
        } catch (Exception e) {

        }
        return CurrentUser.builder().build();
    }

}
