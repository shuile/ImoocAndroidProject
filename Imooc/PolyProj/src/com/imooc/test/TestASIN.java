package com.imooc.test;

public class TestASIN {
    public static void main(String[] args) {
        while (true) {
            double random = Math.random() * 2 - 1;
            double degree = Math.toDegrees(Math.asin(random));
            System.out.println("random: " + random + "  degree: " + degree);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
