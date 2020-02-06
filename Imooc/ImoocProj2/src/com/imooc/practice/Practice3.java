package com.imooc.practice;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Practice3 {

    public static void main(String[] args) {
        //文件名称
        String one = "one.txt";
        String two = "two.txt";

        int n = 0;
        long startTime = System.currentTimeMillis();
        long useTimeOne = 0;
        long useTimeTwo = 0;

        try {
            FileOutputStream oneFos = new FileOutputStream(one, true);
            while (n < 100000) {
                oneFos.write(n++);
            }
            oneFos.close();
            System.out.println("one.txt不使用缓冲流来写");
            useTimeOne = System.currentTimeMillis() - startTime;
            System.out.println("用时为：" + useTimeOne + "ms");
            startTime = System.currentTimeMillis();

            FileOutputStream twoFos = new FileOutputStream(two, true);
            BufferedOutputStream twoBos = new BufferedOutputStream(twoFos);
            n = 0;
            while (n < 100000) {
                twoBos.write(n++);
            }
            twoBos.flush();
            useTimeTwo = System.currentTimeMillis() - startTime;
            System.out.println("two.txt使用缓冲流来写");
            System.out.println("用时为：" + useTimeTwo + "ms");
            System.out.println("节省时间：" + (useTimeOne - useTimeTwo) + "ms");
            twoFos.close();
            twoBos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
