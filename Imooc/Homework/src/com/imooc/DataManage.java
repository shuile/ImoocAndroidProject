package com.imooc;

import java.util.Scanner;

public class DataManage {
    public static void main(String[] args) {
        int[] a = new int[10];
        int flag;
        int length = 9;
        DataManage dm = new DataManage();
        Scanner sc = new Scanner(System.in);
        while (true) {
            dm.notice();
            flag = sc.nextInt();
            if (flag == 0) {
                break;
            }
            switch (flag) {
                case 1:
                    a = dm.insertData();
                    dm.showData(a, length);
                    break;
                case 2:
                    dm.showData(a, length);
                    break;
                case 3:
                    System.out.println("请输入要插入的数据：");
                    int data = sc.nextInt();
                    System.out.println("请输入要插入数据的位置：");
                    int locate = sc.nextInt();
                    length++;
                    dm.insertAtArray(a, data, locate);
                    dm.showData(a, length);
                    break;
                case 4:
                    dm.divThree(a);
                    break;
                default:
                    System.out.println("请输入0-4之间的数字，进行开启功能！");
            }
        }
    }

    /**
     * 显示提示信息
     */
    public void notice() {
        System.out.println("**********************************");
        System.out.println("         1--插入数据");
        System.out.println("         2--显示所有数据");
        System.out.println("         3--在指定位置处插入数据");
        System.out.println("         4--查询能被3整除的数据");
        System.out.println("         0--退出");
        System.out.println("**********************************");
        System.out.println("请输入对应的数字进行操作：");
    }

    /**
     * 插入数据
     */
    public int[] insertData() {
        int[] a = new int[10];
        int temp;
        Scanner sc = new Scanner(System.in);
        for (int i = 0 ; i < 9 ; i++) {
            System.out.println("请输入第" + (i + 1) + "个数据：");
            temp  =sc.nextInt();
            while (temp == 0) {
                System.out.println("请输入第" + (i + 1) + "个数据：");
                temp = sc.nextInt();
            }
            a[i] = temp;
        }
        return a;
    }

    /**
     * 显示所有数据
     */
    public void showData(int[] a, int length) {
        for (int i = 0 ; i < length ; i++) {
            if (a[i] == 0) {
                break;
            }
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    /**
     * 在指定位置处插入数据
     */
    public void insertAtArray(int[]a , int data, int locate) {
        int[] b = a;
        b[locate] = data;
        for (int i = 0 ; i < a.length ; i++) {
            if (i < locate) {
                b[i] = a[i];
            } else if (i > locate) {
                b[i] = a[i-1];
            }
        }
        a = b;
    }

    /**
     * 查询能被3整除的数据
     */
    public void divThree(int[] a) {
        int count = 0;
        System.out.print("数组中能被3整除的元素为：");
        for (int a1 : a) {
            if (a1 % 3 == 0) {
                System.out.print(a1 + "  ");
            } else {
                count++;
            }
        }
        if (count == a.length) {
            System.out.println("数组中不存在能被3整除的元素！");
        } else {
            System.out.println();
        }
    }
}
