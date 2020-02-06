package com.imooc.path;

import java.io.File;
import java.io.IOException;

public class FileDemo {

    public static void main(String[] args) {
        File file = new File("thread/thread.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //是否是绝对路径
        System.out.println(file.isAbsolute());
        //获取相对路径
        System.out.println(file.getPath());
        //获取绝对路径
        System.out.println(file.getAbsolutePath());
        //获取文件名
        System.out.println(file.getName());
    }
}
