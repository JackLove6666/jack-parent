package com.cloud.jack.app.test.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓冲输入文件
 */
public class BufferedInputFile {


    public static String readFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String s = null;
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s+"\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        String readFile = readFile("D:\\工作日志\\学习文档\\test.txt");
        System.out.println(readFile);
    }
}
