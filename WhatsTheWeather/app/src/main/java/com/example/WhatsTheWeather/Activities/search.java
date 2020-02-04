package com.example.WhatsTheWeather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.WhatsTheWeather.Adapters.MyAdapter;
import com.example.WhatsTheWeather.Models.ForecastDisplay;
import com.example.WhatsTheWeather.Models.ListForecast;
import com.example.WhatsTheWeather.Models.WheatherForecast;
import com.example.WhatsTheWeather.Network.RetrofitClientInstance;
import com.example.WhatsTheWeather.Network.WeatherService;
import com.example.WhatsTheWeather.R;
import com.example.WhatsTheWeather.Utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search extends AppCompatActivity {
    private static final String TAG = "testButton";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ForecastDisplay> forecastDisplayList;

    private List<String> testlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

            forecastDisplayList = new ArrayList<>();

            Log.d(TAG, "toto" );
            Log.d(TAG,"size"+forecastDisplayList.size());
            recyclerView = (RecyclerView) findViewById(R.id.listForcast);
            recyclerView.setHasFixedSize(true);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView


            // use a linear layout manager

            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)

            mAdapter = new MyAdapter( (ArrayList<ForecastDisplay>) forecastDisplayList);
            recyclerView.setAdapter(mAdapter);




    }



    public void getMeteo(View view) {
        EditText editText = (EditText) findViewById(R.id.city_search);
        String city = editText.getText().toString();
        getDatasWithRetrofit(city);


    }

    private String getDatasWithRetrofit(String cityName) {

         final WheatherForecast wheatherForecastToAdd;

        WeatherService service = RetrofitClientInstance.getRetrofitInstance().create(WeatherService.class);
        Call<ListForecast> callAsync = service.getForecast(cityName, "cb9b73946c744e86f5c069c97e42af61");


        callAsync.enqueue(new Callback<ListForecast>() {
            @Override
            public void onResponse(Call<ListForecast> call, Response<ListForecast> response) {

                if (response.body() == null) {
                    Log.d(TAG, "onResponseQueryNull: " + "veuillez entrer une ville");
                    return;

                }
                List<WheatherForecast> responseList = response.body().getWeather();



                if (responseList != null) {

                          for (WheatherForecast wheatherForecast:responseList){


                              //Log.d(TAG,wheatherForecast.getDtTxt());


                              ForecastDisplay forecastDisplay = new ForecastDisplay();
                            forecastDisplay.setTemp(wheatherForecast.getMain().getTemp().toString());
                              try {
                                  Date  dateFormat = Utils.StrDateToDateObj(wheatherForecast.getDtTxt());
                                  String time = Utils.ObjDateToTimeStr(dateFormat);
                                  forecastDisplay.setDate(time+"h");
                                  System.out.println(time);

                              } catch (Exception e) {
                                  e.printStackTrace();
                              }

                            forecastDisplayList.add(forecastDisplay);


                          }

                mAdapter.notifyDataSetChanged();




                    return ;


                } else {
                    Log.d(TAG, "onResponse: " + response.raw());

                }

            }

            @Override
            public void onFailure(Call<ListForecast> call, Throwable throwable) {
                System.out.println(throwable);

            }

        });

        return "test";
    }

}