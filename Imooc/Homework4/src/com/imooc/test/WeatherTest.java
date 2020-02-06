package com.imooc.test;

import com.imooc.model.GenerateWeather;
import com.imooc.model.ReadWeather;
import com.imooc.model.Weather;

public class WeatherTest {

    public static void main(String[] args) {
        Weather weather = new Weather();
        new Thread(new GenerateWeather(weather)).start();
        new Thread(new ReadWeather(weather)).start();
    }
}
