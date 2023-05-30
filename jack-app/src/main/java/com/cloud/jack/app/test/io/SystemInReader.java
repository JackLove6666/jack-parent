package com.cloud.jack.app.test.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * System.in.read() 读取控制台输入
 */
public class SystemInReader {

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
