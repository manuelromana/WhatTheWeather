package com.example.getrestaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWeather {
    @SerializedName("coord")
    @Expose
    private String coord;
    @SerializedName("weather")
    @Expose
    private List<String> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    private String wind;
    @SerializedName("clouds")
    @Expose
    private String clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    private String sys;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public List<String> getWeather() {
        return weather;
    }

    public void setWeather(List<String> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }
}
