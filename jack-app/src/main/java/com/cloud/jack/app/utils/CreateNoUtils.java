package com.cloud.jack.app.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.util.DateUtils;

import java.util.Date;

public class CreateNoUtils {



    public static String createNo(String prefix, String data) {

        Date now = new Date();
        String format = DateUtils.format(now, "yyMMdd");
        String start = prefix + format;
        if(StrUtil.isNotEmpty(data)){
            if(data.startsWith(start)){
                String[] split = data.split(start);
                Integer no = Integer.valueOf(split[1]);
                no++;
                return start + splicingLength(no + "");
            }else {
                return start + "00001";
            }
        }else {
            return start + "00001";
        }
    }



    /**
     * 拼接长度 默认为4位
     *
     * @param no
     * @return
     */
    private static String splicingLength(String no) {
        if (no.length() > 4) {
            return no;
        }
        return splicingLength("0" + no);
    }
}
