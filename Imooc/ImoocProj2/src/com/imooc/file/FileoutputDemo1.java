package com.imooc.file;

import java.io.*;

public class FileoutputDemo1 {

    public static void main(String[] args) {
        //文件拷贝
        FileInputStream fis;
        BufferedInputStream bis;
        FileOutputStream fos;
        BufferedOutputStream bos;
        try {
            long startTime = System.currentTimeMillis();
            fis = new FileInputStream("happy.gif");
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream("happycopy.gif");
            bos = new BufferedOutputStream(fos);
            int n = 0;
            byte[] b = new byte[1024];
            while ((n = bis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            System.out.println((System.currentTimeMillis() - startTime));
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
