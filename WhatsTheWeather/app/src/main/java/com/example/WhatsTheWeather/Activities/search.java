package com.example.WhatsTheWeather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.WhatsTheWeather.Adapters.AdapterDays;
import com.example.WhatsTheWeather.Adapters.MyAdapter;
import com.example.WhatsTheWeather.Models.DaysDisplay;
import com.example.WhatsTheWeather.Models.ForecastDisplay;
import com.example.WhatsTheWeather.Models.FullWeather;
import com.example.WhatsTheWeather.Models.ListForecast;
import com.example.WhatsTheWeather.Models.WheatherForecast;
import com.example.WhatsTheWeather.Network.RetrofitClientInstance;
import com.example.WhatsTheWeather.Network.WeatherService;
import com.example.WhatsTheWeather.R;
import com.example.WhatsTheWeather.Utils.Utils;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search extends AppCompatActivity {
    private static final String TAG = "testButton";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ForecastDisplay> forecastDisplayList;

    private List<DaysDisplay> daysDisplaylist;
    private RecyclerView recyclerViewDay;
    private RecyclerView.Adapter mAdapterDay;
    private RecyclerView.LayoutManager layoutManagerDay;

    TextView txtTempMax,txtTempMin, txtVentSpeed,txtHumidity,txtPression,txtVentDir,txtCelcius,txtFahreinheit;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // display Main Infos

        txtTempMax = (TextView) findViewById(R.id.textViewTempCelciusMax);
        txtTempMin= (TextView) findViewById(R.id.textViewTempCelciusMin);
        txtCelcius = (TextView) findViewById(R.id.textViewTempCelcius);
        txtFahreinheit = (TextView) findViewById(R.id.textViewTempFar);
        txtVentDir = (TextView) findViewById(R.id.textViewVentDir);
        txtVentSpeed = (TextView) findViewById(R.id.textViewVentSpeed);
        txtPression = (TextView) findViewById(R.id.textViewPressure);
        txtHumidity = (TextView) findViewById(R.id.textViewHumidity);




        //display list temp of the day
        forecastDisplayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.listForcast);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter
        mAdapter = new MyAdapter((ArrayList<ForecastDisplay>) forecastDisplayList);
        recyclerView.setAdapter(mAdapter);

        //display list of day with infos
        daysDisplaylist = new ArrayList<>();
        recyclerViewDay = (RecyclerView) findViewById(R.id.recycle_day);
        recyclerViewDay.setHasFixedSize(true);
        layoutManagerDay = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewDay.setLayoutManager(layoutManagerDay);
        mAdapterDay = new AdapterDays((ArrayList<DaysDisplay>) daysDisplaylist);
        recyclerViewDay.setAdapter(mAdapterDay);

    }


    public void getMeteo(View view) {
        EditText editText = (EditText) findViewById(R.id.city_search);
        String city = editText.getText().toString();
        getDatasWithRetrofit(city);
        getInfosToday(city);

    }

    //get wheather forecast
    private String getDatasWithRetrofit(String cityName) {
        WeatherService service = RetrofitClientInstance.getRetrofitInstance().create(WeatherService.class);
        Call<ListForecast> callAsync = service.getForecast(cityName, "cb9b73946c744e86f5c069c97e42af61","metric");


        callAsync.enqueue(new Callback<ListForecast>() {
            @Override
            public void onResponse(Call<ListForecast> call, Response<ListForecast> response) {

                if (response.body() == null) {
                    Log.d(TAG, "onResponseQueryNull: " + "veuillez entrer une ville");
                    return;

                }
                List<WheatherForecast> responseList = response.body().getWeather();


                if (responseList != null) {

                    //Log.d(TAG,wheatherForecast.getDtTxt());
                    //Log.d(TAG, wheatherForecast.getMain().getTemp().toString());

                    //  température
                    //clean list before add to get only the fresh request
                    forecastDisplayList.clear();

                    // loop for temp of today
                    for (WheatherForecast wheatherForecast : responseList) {
                        try {
                            Date dateFormatted = Utils.StrDateToDateObj(wheatherForecast.getDtTxt());
                            int diff = Utils.CompareDatetoTodayDateInHour(dateFormatted);

                            if (diff <48){ //a changer pour n'avoir qu'une journée
                                //cration d'un objet pour le display
                                ForecastDisplay forecastDisplay = new ForecastDisplay();

                                //get time in str from Date and set to display
                                String time = Utils.ObjDateToTimeStr(dateFormatted);
                                forecastDisplay.setDate(time);

                                // get temp in °C

                                Double temp_C = wheatherForecast.getMain().getTemp();
                                Double temp_F = temp_C*1.8 +32;
                                forecastDisplay.setTemp_celsius(temp_C.toString());
                                forecastDisplay.setTemp(temp_F.toString());
                                forecastDisplayList.add(forecastDisplay);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }

                    //loop for the day
                    for (WheatherForecast wheatherForecast : responseList) {
                        String StringDate = wheatherForecast.getDtTxt();
                        try {
                            //get date format from data elt
                            Date dateFormatted = Utils.StrDateToDateObj(StringDate);

                            //get day from date
                            SimpleDateFormat dayOfWeek = new SimpleDateFormat("EE");
                            String day= dayOfWeek.format(dateFormatted);

                            //get time
                            String time = Utils.ObjDateToTimeStr(dateFormatted);

                            //get temp °C
                            String temp = wheatherForecast.getMain().getTemp().toString();


                            //create and add datas in str to model for day
                            DaysDisplay dayDisplay = new DaysDisplay();
                            dayDisplay.setDay(day);
                            dayDisplay.setHour(time);
                            dayDisplay.setTemp(temp);

                           //retrieve display list for day
                              daysDisplaylist.add(dayDisplay);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    mAdapter.notifyDataSetChanged();
                    //mAdapterDay.notifyDataSetChanged();




                    return;


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

    private void getInfosToday(String cityName) {


        WeatherService service = RetrofitClientInstance.getRetrofitInstance().create(WeatherService.class);
        Call<FullWeather> callAsync = service.getFullWeather(cityName, "cb9b73946c744e86f5c069c97e42af61","metric");

        callAsync.enqueue(new Callback<FullWeather>() {
            @Override
            public void onResponse(Call<FullWeather> call, Response<FullWeather> response) {

                if (response.body() == null) {
                    Log.d(TAG, "onResponseQueryNull: " + "veuillez entrer une ville");
                    return;

                }
                FullWeather fullWeather = response.body();


                if (fullWeather != null) {

                    Double tempC = fullWeather.getMain().getTemp();
                    Double tempF = tempC*1.8 +32;

                    txtCelcius.setText(String.format("%.2f",tempC)+"°C");
                    txtFahreinheit.setText(String.format("%.2f",tempF)+"°F");

                    txtTempMax.setText("TempMax :"+String.format("%.2f",fullWeather.getMain().getTempMax())+"°C");
                    txtTempMin.setText("TempMin :"+String.format("%.2f",fullWeather.getMain().getTempMin())+"°C");

                    txtVentDir.setText(String.format("Vent direction :"+"%s",fullWeather.getWind().getDeg())+"° (meteo)");
                    txtVentSpeed.setText("Vent speed" +fullWeather.getWind().getSpeed()+"m/sec");

                    txtHumidity.setText("Humidity: "+fullWeather.getMain().getHumidity()+"%");
                    txtPression.setText("Pression :"+fullWeather.getMain().getPressure()+"Pa");

                    return;


                } else {
                    Log.d(TAG, "onResponse: " + response.raw());

                }

            }

            @Override
            public void onFailure(Call<FullWeather> call, Throwable throwable) {
                System.out.println(throwable);
            }

        });

    }


}