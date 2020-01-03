package com.example.WhatsTheWeather.Network;

import com.example.WhatsTheWeather.Models.FullWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("/data/2.5//weather")
    public Call<FullWeather> getFullWeather(
            @Query("q") String cityName,
            @Query("appid") String apiKey
    );

}
