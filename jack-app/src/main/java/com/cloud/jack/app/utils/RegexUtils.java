package com.cloud.jack.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {


    public static Boolean matchData(String regex, String data) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(data);
        if (m.matches()) {
            return true;
        }
        return false;
    }

}
