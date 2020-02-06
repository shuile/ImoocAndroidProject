package com.imooc.tel;

public class Computer implements INet {
//    public void network() {
//        System.out.println("电脑可以上网");
//    }

    public void game() {
        System.out.println("电脑可以玩游戏");
    }

    @Override
    public void network() {
        System.out.println("电脑可以上网");
    }

    @Override
    public void connection() {

    }
}
