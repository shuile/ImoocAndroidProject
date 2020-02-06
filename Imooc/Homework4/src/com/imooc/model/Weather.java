package com.imooc.model;

public class Weather {

    private int temperature;    // 温度
    private int humidity;   // 湿度
    private boolean flag = false;   // 标志位

    public Weather() {
    }

    // 构造方法
    public Weather(int temperature, int humidity) {
        super();
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // getter和setter方法
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    // 生成天气方法
    public synchronized void generate() {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        temperature = (int) (Math.random() * 41);
        humidity = (int) (Math.random() * 101);
        System.out.println("生成天气数据" + toString());
        flag = true;
        notifyAll();
    }

    // 获取天气方法
    public synchronized void read() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("读取天气数据" + toString());
        flag = false;
        notifyAll();
    }

    // 重写toString
    @Override
    public String toString() {
        return "[温度：" + temperature + ",湿度：" + humidity + "]";
    }
}
