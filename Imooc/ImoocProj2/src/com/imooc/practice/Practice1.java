package com.imooc.practice;

import java.io.File;
import java.io.IOException;

public class Practice1 {

    public static void main(String[] args) {
        File file = new File("/Users/shui/Desktop/MyAndroidProject/File");
        File file1 = new File(file, "Monday.docx");
        if (!file1.exists()) {
            try {
                boolean newFile = file1.createNewFile();
                if (newFile) {
                    System.out.println("文件创建成功！");
                } else {
                    System.out.println("文件创建失败！");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("文件名称：" + file1.getName());
            System.out.println("文件上一级目录：" + file1.getParentFile().getName());
        }

        if (file1.exists()) {
            String content1 = "";
            content1 = file1.isFile() ? "文件" : content1;
            content1 = file1.isDirectory() ? "目录" : content1;
            System.out.println("文件/目录：这是一个" + content1);

            boolean canRead = file1.canRead();
            boolean canWrite = file1.canWrite();
            String content2 = "";
            if (canRead) {
                if (canWrite) {
                    content2 = "既可以读还可以写";
                } else {
                    content2 = "只能读";
                }
            } else {
                if (canWrite) {
                    content2 = "只能写";
                } else {
                    content2 = "既不能读也不能写";
                }
            }
            System.out.println("读写性：这个" + content1 + content2);
        }
    }
}
