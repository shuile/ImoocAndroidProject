package com.imooc.method;

import java.util.Scanner;

public class ArrayOperator {

    public static void main(String[] args) {
        int[] arr = new int[9];
        ArrayOperator ao = new ArrayOperator();
        Scanner sc = new Scanner(System.in);
        while (true) {
            ao.notice();
            int input = sc.nextInt();
            if (input == 1) {
                ao.initData(arr);
            }
            if (input == 2) {
                ao.showData(arr);
            }
            if (input == 3) {
                ao.insertData(arr);
            }
            if (input == 4) {
                ao.queryData(arr);
            }
            if (input == 0) {
                break;
            }
        }
    }

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

    /*
      初始化数据
     */
    public void initData(int[] arr) {
        for (int i = 0 ; i < arr.length ; i++) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入第" + (i + 1) + "个数据：");
            arr[i] = sc.nextInt();
        }
        showData(arr);
    }

    /*
      显示数据
     */
    public void showData(int[] arr) {
        System.out.println("数组元素为：");
        for (int a : arr) {
            System.out.print(a + "  ");
        }
        System.out.println();
    }

    /*
      插入数据
     */
    public void insertData(int[] arr) {
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入要插入的数据：");
        int data = sc.nextInt();
        System.out.println("请输入要插入数据的位置：");
        int locate = sc.nextInt();
        arr[locate - 1] = data;
        showData(arr);
    }

    /*
      查询能被3整除的数据
     */
    public void queryData(int[] arr) {
        System.out.print("数组中能被3整除的元素为：");
        for (int a : arr) {
            if (a % 3 == 0) {
                System.out.print(a + "  ");
            }
        }
        System.out.println();
    }
}
