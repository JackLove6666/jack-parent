package com.cloud.jack.app.test.io;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

@Data
@AllArgsConstructor
public class User implements Serializable {


    private String name;

    private int age;

    private transient String password;

    public User() {
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ",age=" + age +
                ",password='" + password + '\'' +
                "}";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User jack = new User("jack", 18, "123456");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\工作日志\\学习文档\\test.txt"));
        objectOutputStream.writeObject(jack);
        objectOutputStream.close();

        //再从文件中读取对象
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\工作日志\\学习文档\\test.txt"));
        User o = (User) objectInputStream.readObject();
        System.out.println(o);
        objectInputStream.close();
    }
}
