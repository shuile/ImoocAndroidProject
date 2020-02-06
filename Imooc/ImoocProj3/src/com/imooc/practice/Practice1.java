package com.imooc.practice;

import java.io.*;

public class Practice1 {

    public static void main(String[] args) {
        String fileName = "practice1.txt";
        String content = "";
        for (int i = 0; i < 100000; i++) {
            content += i + " ";
        }
        boolean write = transWriteByBuf(fileName, content);
        System.out.println("写结果：" + write);
        String read = transReadByBuf(fileName);
        System.out.println(read);

    }

    //写文件
    public static boolean transWriteByBuf(String fileName, String conetnt) {
        FileOutputStream fos;
        OutputStreamWriter osw;
        BufferedWriter bw;
        try {
            fos = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(conetnt);
            bw.flush();
            fos.close();
            osw.close();
            bw.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //读文件
    public static String transReadByBuf(String fileName) {
        String content = "";
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        String str = null;
        try {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                content += str;
            }
            fis.close();
            isr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content;
    }
}
