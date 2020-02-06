package com.example.WhatsTheWeather.Models;

public class DaysDisplay {

    public String Day ;
    public String hour;
    public String temp;
    public String icon;

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
