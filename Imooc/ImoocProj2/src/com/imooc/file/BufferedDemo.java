package com.imooc.file;

import java.io.*;

public class BufferedDemo {

    public static void main(String[] args) {
        FileOutputStream fos;
        BufferedOutputStream bos;
        FileInputStream fis;
        BufferedInputStream bis;
        try {
            fos = new FileOutputStream("imooc.txt");
            bos = new BufferedOutputStream(fos);
            fis = new FileInputStream("imooc.txt");
            bis = new BufferedInputStream(fis);
            bos.write(50);
            bos.write('a');
            bos.flush();
            System.out.println(bis.read());
            System.out.println((char) bis.read());
            bos.close();
            fis.close();
            fos.close();
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
