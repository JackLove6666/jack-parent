package com.cloud.jack.app.test.io;

import java.io.*;

/**
 * FilterInputStream 是所有输入流的抽象装饰类，它的子类可以为“所有输入流”提供装饰功能。
 */
public class InputStreamTest {


    public static void main(String[] args) throws IOException {
        File file = new File("D:\\工作日志\\学习文档\\设计模式.md");
        System.out.println(file.getName());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        while (bufferedReader.read() != -1) {
            String readLine = bufferedReader.readLine();
            System.out.println(readLine);
        }

        while (bufferedInputStream.read() != -1) {
            System.out.println(bufferedInputStream.read());
        }


    }
}
