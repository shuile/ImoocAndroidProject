package com.imooc.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Practice2 {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("speech.txt");
            int n = 0;
            int count = 0;
            while ((n = fis.read()) != -1) {
                count++;
            }
            System.out.println("统计结果：speech.txt文件中共有" + count + "个字节。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
