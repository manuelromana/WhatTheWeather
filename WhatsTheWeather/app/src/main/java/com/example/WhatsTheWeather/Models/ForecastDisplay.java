package com.example.WhatsTheWeather.Models;

public class ForecastDisplay {
    public String temp; //Fareinheit
    public String temp_celsius;
    public String date;


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_celsius() {
        return temp_celsius;
    }

    public void setTemp_celsius(String temp) {
        this.temp_celsius = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
