package com.example.getrestaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestaurantApi {
    @GET("restaurants/getrestaurants")
    Call<List<Restaurant>> getRestaurants();

    @POST("restaurants/addrestaurants")
    Call<String> postRestaurants (@Body Restaurant restaurant);
}
