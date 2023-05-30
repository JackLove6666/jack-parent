package com.cloud.jack.app.test.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * 格式化内存输入
 */
public class FormattedMemoryInput {


    public static void main(String[] args) throws IOException {
        String property = System.getProperty("user.dir");
        System.out.println(property);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.readFile("D:\\工作日志\\学习文档\\test.txt").getBytes()));
        while (dataInputStream.available() != 0) {
            System.out.println((char) dataInputStream.readByte());
        }
    }
}
