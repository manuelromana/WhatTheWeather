package com.example.getrestaurant;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {
    @GET("q=London,uk&weather?appid=b6907d289e10d714a6e88b30761fae22")
    Call<CityWeather> getJsonObjectData();
}
