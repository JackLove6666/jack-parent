package com.cloud.jack.app.test.io;

import java.io.File;

/**
 * 目录实用工具
 */
public class Directory {

    public static File[] getLocalFiles(File dir,final String regex){
        return dir.listFiles((dir1,name)->name.matches(regex));
    }

    public static File[] getLocalFiles(String path,final String regex){
        return getLocalFiles(new File(path),regex);
    }

    public static void main(String[] args) {
        String path = "D:\\工作日志\\学习文档";
        File[] localFiles = getLocalFiles(path, ".*\\.md");
        for (File localFile : localFiles) {
            System.out.println(localFile.getName());
        }
    }
}
