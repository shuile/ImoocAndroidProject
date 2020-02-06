package com.imooc.model;

// 获取天气类
public class ReadWeather implements Runnable {

    private Weather weather;

    public ReadWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public void run() {
        int i = 1 ;
        while (i <= 100) {
            weather.read();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
