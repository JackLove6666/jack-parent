package com.cloud.jack.app.test;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DataTimeFormatterTest {

    public static void main(String[] args) {
        String str = "2023-03-28T01:23:34+00:00";
        TemporalAccessor parse = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX").parse(str);
        LocalDateTime localDateTime = LocalDateTime.from(parse);
        System.out.println(localDateTime);
    }
}
