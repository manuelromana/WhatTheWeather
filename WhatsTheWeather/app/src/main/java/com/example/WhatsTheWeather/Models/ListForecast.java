package com.example.WhatsTheWeather.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListForecast {
    @SerializedName("list")
    @Expose
    private List <WheatherForecast> wheatherForecastList;

    public List<WheatherForecast> getWeather() {
        return wheatherForecastList;
    }

    public void setWeather(List<WheatherForecast> wheatherForecastList) {
        this.wheatherForecastList = wheatherForecastList;
    }
}
