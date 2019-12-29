package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private UserService userService;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configRetrofit();

    }


    private void configRetrofit (){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://samples.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<FullWeather> callAsync = service.getFullWeather("London","b6907d289e10d714a6e88b30761fae22");

        callAsync.enqueue(new Callback<FullWeather>() {
            @Override
            public void onResponse(Call<FullWeather> call, Response<FullWeather> response) {
               FullWeather fullWeather = response.body();
                List<Weather> wheathers = fullWeather.getWeather();

               if (fullWeather != null){
                    for (Weather weather : wheathers){
                        Log.d(TAG, "onResponse: "+ weather.getDescription()+" " +weather.getMain()+" "+ weather.getIcon());
                    }
               }else{
                   Log.d(TAG, "onResponse: "+response.raw());

               }

            }

            @Override
            public void onFailure(Call<FullWeather> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });

    }
}
