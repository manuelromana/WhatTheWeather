package com.example.testapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FullWeather {


        @SerializedName("coord")
        @Expose
        private Coord coord;
        @SerializedName("weather")
        @Expose
        private List<Weather> weather = null;
        @SerializedName("base")
        @Expose
        private String base;
        @SerializedName("main")
        @Expose
        private Main main;
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

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }



        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
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
