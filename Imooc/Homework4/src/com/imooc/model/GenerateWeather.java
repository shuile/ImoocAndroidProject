package com.imooc.model;

//生成天气类
public class GenerateWeather implements Runnable {

    private Weather weather;

    public GenerateWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= 100) {
            weather.generate();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
