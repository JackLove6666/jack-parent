package com.cloud.jack.app.test.io;

import java.io.*;

/**
 * 存储和恢复数据
 */
public class StoringAndRecoveringData {


    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
        dataOutputStream.writeDouble(3.1415926);
        dataOutputStream.writeUTF("That was pi");
        dataOutputStream.writeInt(250);
        dataOutputStream.close();

        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("data.txt")));
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readInt());
        dataOutputStream.close();
    }
}
