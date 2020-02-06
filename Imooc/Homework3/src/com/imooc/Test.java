package com.imooc;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        int isContinuePlaying = 1;
        int selectMenuOption;
        Animal animal;
        Scanner sc = new Scanner(System.in);
        while (isContinuePlaying == 1) {
            showMenu();
            selectMenuOption = sc.nextInt();
            if (selectMenuOption == 1) {
                animal = new Bear("Bill", 1);
                ((Bear) animal).act();
            } else if (selectMenuOption == 2) {
                animal = new Lion("Lain", 2, "灰色", "公狮");
                ((Lion) animal).act();
            } else if (selectMenuOption == 3) {
                animal = new Monkey("Tom", 1, "金丝猴");
                ((Monkey) animal).act();
            } else if (selectMenuOption == 4) {
                animal = new Parrot("Rose", 1, "牡丹鹦鹉");
                ((Parrot) animal).act();
            } else if (selectMenuOption == 5) {
                Clown clown = new Clown("Kahle", 5);
                clown.act();
            } else {
                System.out.println("** 输入信息不正确，请重新输入 **");
                continue;
            }
            System.out.println("******  是否继续观看(1/0)  ******");
            isContinuePlaying = sc.nextInt();
            while (!(isContinuePlaying == 1 || isContinuePlaying == 0)) {
                System.out.println("** 输入信息不正确，请重新输入 **");
                System.out.println("******  是否继续观看(1/0)  ******");
                isContinuePlaying = sc.nextInt();
            }
        }
        System.out.println("*******      欢迎下次光临      *******");
    }

    private static void showMenu() {
        System.out.println("********欢迎来到太阳马戏团********");
        System.out.println("**********   请选择表演者**********");
        System.out.println("**********     1、棕熊     **********");
        System.out.println("**********     2、狮子     **********");
        System.out.println("**********     3、猴子     **********");
        System.out.println("**********     4、鹦鹉     **********");
        System.out.println("**********     5、小丑     **********");
    }
}
