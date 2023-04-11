package com.cloud.jack.core.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.cloud.jack.core.R;

import java.util.HashMap;


/**
 * 捷克梗
 * 1.Http Get Post
 * 2.包装头部 Header、
 */
public class HttpClientUtils {

    public static R get(){
        HttpRequest httpRequest = HttpUtil.createGet("index");
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("","");
        httpRequest.addHeaders(headerMap);
        HttpResponse httpResponse = httpRequest.execute();
        System.out.println(httpResponse);
        return new R(httpResponse);
    }

}
