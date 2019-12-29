package com.example.openweather.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.openweather.Models.FullWeather;
import com.example.openweather.Models.WeatherInfos;
import com.example.openweather.Network.RetrofitClientInstance;
import com.example.openweather.Network.WeatherService;
import com.example.openweather.R;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMeteo extends AppCompatActivity {

    private static final String TAG = "LogWeather";
    private String meteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_meteo);


        String cityName = getIntent().getStringExtra("cityName");
        getDatasWithRetrofit(cityName);



    }

    private void getDatasWithRetrofit (String cityName){


        WeatherService service = RetrofitClientInstance.getRetrofitInstance().create(WeatherService.class);
        Call<FullWeather> callAsync = service.getFullWeather(cityName,"b6907d289e10d714a6e88b30761fae22");

        callAsync.enqueue(new Callback<FullWeather>() {
            @Override
            public void onResponse(Call<FullWeather> call, Response<FullWeather> response) {
                FullWeather fullWeather = response.body();
                List<WeatherInfos> wheatherInfosList = fullWeather.getWeatherInfosList();

                if (fullWeather != null){
                    for (WeatherInfos weatherElt : wheatherInfosList){
                        Log.d(TAG, "onResponse: "+ weatherElt.getDescription()+" " +weatherElt.getMain()+" "+ weatherElt.getIcon()+" "+response.raw());

                        TextView resultText = (TextView) findViewById(R.id.meteo);
                        resultText.setText(weatherElt.getDescription());
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
