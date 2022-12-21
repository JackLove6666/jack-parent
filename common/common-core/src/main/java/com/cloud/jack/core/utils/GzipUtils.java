package com.cloud.jack.core.utils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {

    private static final int BUFFER = 1024;

    /**
     * 压缩
     *
     * @param orginalFile
     * @param compressFile
     * @throws IOException
     */
    public static void compress(File orginalFile, File compressFile) throws IOException {
        if (orginalFile != null && orginalFile.isFile() && compressFile != null && compressFile.isFile()) {
            FileInputStream is = new FileInputStream(orginalFile);
            FileOutputStream os = new FileOutputStream(compressFile);
            compress(is, os);
            is.close();
            os.close();
        }
    }

    /**
     * 压缩
     *
     * @param orginalFile
     * @param compressFile
     * @throws IOException
     */
    public static void compress(String orginalFile, String compressFile) throws IOException {
        if (orginalFile != null && compressFile != null) {
            FileInputStream is = new FileInputStream(orginalFile);
            FileOutputStream os = new FileOutputStream(compressFile);
            compress(is, os);
            is.close();
            os.close();
        }
    }

    /**
     * 压缩
     *
     * @param bytes
     * @return
     * @throws IOException
     */
    public static byte[] compress(byte[] bytes) throws IOException {
        if (bytes != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            compress(bais, baos);
            bytes = baos.toByteArray();
            bais.close();
            baos.close();
        }
        return bytes;
    }

    /**
     * 压缩
     *
     * @param is
     * @param os
     * @throws IOException
     */
    public static void compress(InputStream is, OutputStream os) throws IOException {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        int len = 0;
        byte[] buffer = new byte[BUFFER];
        while ((len = is.read(buffer)) != -1) {
            gos.write(buffer, 0, len);
        }
        gos.finish();
        gos.flush();
        gos.close();
    }

    /**
     * 解压
     *
     * @param compressFile
     * @param orginalFile
     * @throws IOException
     */
    public static void deCompress(File compressFile, File orginalFile) throws IOException {
        if (orginalFile != null && orginalFile.isFile() && compressFile != null && compressFile.isFile()) {
            FileInputStream is = new FileInputStream(compressFile);
            FileOutputStream os = new FileOutputStream(orginalFile);
            deCompress(is, os);
            is.close();
            os.close();
        }
    }

    /**
     * 解压
     *
     * @param compressFile
     * @param orginalFile
     * @throws IOException
     */
    public static void deCompress(String compressFile, String orginalFile) throws IOException {
        if (orginalFile != null && compressFile != null) {
            FileInputStream is = new FileInputStream(compressFile);
            FileOutputStream os = new FileOutputStream(orginalFile);
            deCompress(is, os);
            is.close();
            os.close();
        }
    }

    /**
     * 解压
     *
     * @param bytes
     * @throws IOException
     */
    public static byte[] deCompress(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        deCompress(bais, baos);
        bytes = baos.toByteArray();
        bais.close();
        baos.close();
        return bytes;
    }

    /**
     * 解压
     *
     * @param is
     * @param os
     * @throws IOException
     */
    public static void deCompress(InputStream is, OutputStream os) throws IOException {
        GZIPInputStream gis = new GZIPInputStream(is);
        int len = 0;
        byte[] buffer = new byte[BUFFER];
        while ((len = gis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.flush();
        gis.close();
    }

}
