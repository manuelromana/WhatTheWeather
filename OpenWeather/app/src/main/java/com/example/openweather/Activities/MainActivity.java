package com.example.openweather.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.openweather.Models.FullWeather;
import com.example.openweather.Models.WeatherInfos;
import com.example.openweather.Network.RetrofitClientInstance;
import com.example.openweather.R;
import com.example.openweather.Network.WeatherService;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {


    private Button getMeteoButton;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getMeteoButton = (Button) findViewById(R.id.GetData);
        this.searchText = (EditText) findViewById(R.id.CityName);

        getMeteoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchText.getText().toString().trim();
                startActivityGetMeteo(cityName);
            }
        });


    }




    private void startActivityGetMeteo(String cityName ){
        Intent intent = new Intent(this, GetMeteo.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }
}
