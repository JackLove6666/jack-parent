package com.cloud.jack.app.test.io;

import java.io.*;

/**
 * 基本的文件输出
 */
public class BasicFileOutput {

    static String file = "D:\\工作日志\\学习文档\\BasicFileOutput.out";


    public static void main(String[] args) throws IOException {
        String readFile = BufferedInputFile.readFile("D:\\工作日志\\学习文档\\BasicFileOutput.java");
        StringReader stringReader = new StringReader(readFile);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        int lineCount = 1;
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            printWriter.println(lineCount++ + ":" + s);
        }
        printWriter.close();
        bufferedReader.close();
    }
}
