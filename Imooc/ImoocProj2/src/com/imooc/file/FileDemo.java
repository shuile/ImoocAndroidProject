package com.imooc.file;

import java.io.File;
import java.io.IOException;

public class FileDemo {

    public static void main(String[] args) {
        //创建File对象
//        File file1 = new File("/Users/shui/Desktop/MyAndroidProject/File/score.txt");
//        File file1 = new File("/Users/shui", "Desktop/MyAndroidProject/File/score.txt");
        File file = new File("/Users/shui/Desktop/MyAndroidProject/File");
        File file1 = new File(file, "score.txt");
        //判断是文件还是目录
        System.out.println("是否是目录：" + file1.isDirectory());
        System.out.println("是否是文件：" + file1.isFile());
        //创建文件
        File file2 = new File(file, "HashSet");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        //创建文件
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
