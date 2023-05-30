package com.cloud.jack.app.test.io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * 压缩文件
 */
public class ZipFileUtils {

    public static void compressFiles(File[] files, String zipPath) throws IOException {
//定义文件输出流，表明是要压缩成zip文件
        FileOutputStream fileOutputStream = new FileOutputStream(zipPath);
//给输出流加上校验功能
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
//定义zip格式的输出流，这里要明白一直在使用装饰器模式在给流添加功能
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
//增加缓冲功能，提高性能
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);

        zipOutputStream.setComment("测试压缩文件");

        for (File file : files) {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
          zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
          int c;
          while ((c = bufferedReader.read()) != -1) {
              bufferedOutputStream.write(c);
          }

          bufferedReader.close();
          bufferedOutputStream.flush();
        }
        bufferedOutputStream.close();
    }

    public static void uncompressZip(String zipPath,String targetPath) throws IOException {
        if(!targetPath.endsWith(File.separator)){
            targetPath = targetPath + File.separator;
            File targetFile = new File(targetPath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
        }
        FileInputStream fileInputStream = new FileInputStream(zipPath);

        CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new Adler32());

        ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);

        ZipEntry zipEntry = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            System.out.println("解压中" + zipEntry.getName());
            String fileName = zipEntry.getName();
            File file = new File(targetPath + fileName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream,buffer.length);
            int c;

            while ((c = bufferedInputStream.read(buffer,0,buffer.length)) != -1) {
                bufferedOutputStream.write(buffer,0,c);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        bufferedInputStream.close();
        //输出校验和
        System.out.println("校验和："+checkedInputStream.getChecksum().getValue());
    }

    public static void uncompressZip2() throws IOException {
        ZipFile zipFile = new ZipFile("D:\\工作日志\\学习文档\\test.zip");
        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            System.out.println("解压中" + zipEntry.getName());
            String fileName = zipEntry.getName();
            File file = new File("D:\\工作日志\\学习文档\\test1\\" + fileName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            InputStream inputStream = zipFile.getInputStream(zipEntry);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream,buffer.length);
            int c;

            while ((c = inputStream.read(buffer,0,buffer.length)) != -1) {
                bufferedOutputStream.write(buffer,0,c);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
    }


    public static void main(String[] args) throws IOException {
        String path = "D:\\工作日志\\学习文档";
        File[] localFiles = Directory.getLocalFiles(path, ".*\\.md");
        try {
            compressFiles(localFiles, "D:\\工作日志\\学习文档\\test.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            uncompressZip("D:\\工作日志\\学习文档\\test.zip","D:\\工作日志\\学习文档\\test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        uncompressZip2();
    }


}
