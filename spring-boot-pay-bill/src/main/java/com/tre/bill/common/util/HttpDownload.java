package com.tre.bill.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author 10097454
 * @Date 2020/05/21
 * @Description TODO
 */
public class HttpDownload {
    /**
     * 根据http地址下载.
     *
     * @param httpUrl  下载地址
     * @param fileName 文件名
     * @param savePath 保存路径
     */
    public static boolean download(String httpUrl, String fileName, String savePath) {
        // 转换为utf8,防止中文路径无法下载
        httpUrl = toUtf8(httpUrl);
        boolean isOK = false;//默认下载失败
        URL url = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream fos = null;

        try {
            url = new URL(httpUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000); // 设置超时时间为10秒
            inputStream = conn.getInputStream(); // 获得下载下来的输入流

            // 将输入流转换为字节数组
            byte[] byteData = readInputStream(inputStream);

            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }

            File file = new File(saveDir + File.separator + fileName);
            fos = new FileOutputStream(file);
            fos.write(byteData);
            isOK = true;
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isOK;
    }

    /**
     * 读取输入流为字节数组.
     *
     * @param inputStream 待读取流
     * @return
     */
    public static byte[] readInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bos.toByteArray();
    }

    /**
     * 转换为utf8编码.
     *
     * @param s 待转换字符串
     * @return
     */
    public static String toUtf8(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String url = "https://openapi.tianquetech.com/capital/fileDownload/download?downUrl=https%3A%2F%2Fosscdn.suixingpay.com%2F0%2F2%2F32MDJhYjIyYWZ6N0NhbzJheDIwMjBfMDVfMjFfMDRfMDBfMGQ2ZGEzMDI0M2Q4NDE2MmJmMzNlOGViNzIxMWEzODYyYUtpY21fYnVja2V0MmFiMQ.csv";
        HttpDownload.download(url, "suixingpay_trade.csv", "D:\\");
    }
}
