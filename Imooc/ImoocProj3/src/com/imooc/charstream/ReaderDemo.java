package com.imooc.charstream;

import java.io.*;

public class ReaderDemo {

    public static void main(String[] args) {
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        FileOutputStream fos;
        OutputStreamWriter osw;
        BufferedWriter bw;
        try {
            fis = new FileInputStream("imooc.txt");
            isr = new InputStreamReader(fis, "GBK");
            br = new BufferedReader(isr);
            fos = new FileOutputStream("imooc1.txt");
            osw = new OutputStreamWriter(fos, "GBK");
            bw = new BufferedWriter(osw);
            int n = 0;
            char[] cbuf = new char[10];
//            while ((n = isr.read()) != -1) {
//                System.out.print((char) n);
//            }
//            while ((n = isr.read(cbuf)) != -1) {
//                String s = new String(cbuf, 0, n);
//                System.out.print(s);
//            }
            while ((n = br.read(cbuf)) != -1) {
//                String s = new String(cbuf, 0, n);
                bw.write(cbuf, 0, n);
            }
            bw.flush();
            fis.close();
            fos.close();
            isr.close();
            osw.close();
            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
