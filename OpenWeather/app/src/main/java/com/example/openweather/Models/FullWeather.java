package com.example.openweather.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FullWeather {


        @SerializedName("coord")
        @Expose
        private Coord coord;
        @SerializedName("weather")
        @Expose
        private List<WeatherInfos> weatherInfosList = null;
        @SerializedName("base")
        @Expose
        private String base;
        @SerializedName("main")
        @Expose
        private MainInfos mainInfos;
        @SerializedName("visibility")
        @Expose
        private Integer visibility;
        @SerializedName("wind")
        @Expose
        private Wind wind;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;


        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public List<WeatherInfos> getWeatherInfosList() {
            return weatherInfosList;
        }

        public void setWeatherInfosList(List<WeatherInfos> weather) {
            this.weatherInfosList = weather;
        }



        public MainInfos getMain() {
            return mainInfos;
        }

        public void setMain(MainInfos main) {
            this.mainInfos = main;
        }

        public Integer getVisibility() {
            return visibility;
        }

        public void setVisibility(Integer visibility) {
            this.visibility = visibility;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
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




}
