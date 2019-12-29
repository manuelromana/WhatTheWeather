package com.example.testapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("/users")
    public Call<List<User>> getUsers();


    @GET("/users/{username}")
    public Call<User> getUser(@Path("username") String username);
}