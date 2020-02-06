package com.imooc.test;

public interface Ifather2 {
    void fly();

    default void connection() {
        System.out.println("IFather");
    }
}
