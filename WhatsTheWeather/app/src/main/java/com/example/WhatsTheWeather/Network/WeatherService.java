package com.example.WhatsTheWeather.Network;

import com.example.WhatsTheWeather.Models.FullWeather;
import com.example.WhatsTheWeather.Models.ListForecast;
import com.example.WhatsTheWeather.Models.WheatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {


    @GET("/data/2.5/weather")
    public Call<FullWeather> getFullWeather(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units

    );
    @GET("/data/2.5/weather")
    public Call<FullWeather> getFullWeatherWithCoord(
            @Query("lat") String lat,
            @Query("lon") String lng,
            @Query("appid") String apiKey,
            @Query("units") String units

    );

    @GET("/data/2.5/forecast/")
    public Call<ListForecast> getForecast(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units

    );


}
