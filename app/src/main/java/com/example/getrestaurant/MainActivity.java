package com.example.getrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Restaurant> restaurants;
    private Button addRestaurantButton;
    private EditText editText;
    private MyListViewAdpater myListViewAdpater;
    private ListView listView;

    private Retrofit retrofit;
    private WeatherApi weatherApi;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurants = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.listview);
        this.addRestaurantButton = (Button) findViewById(R.id.buttonAddRestaurant);
        this.editText = (EditText) findViewById(R.id.et_restname);
        this.editText.setInputType(InputType.TYPE_NULL);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Mon restaurant");
        restaurant.setId("4321");
        restaurants.add(restaurant);

        this.myListViewAdpater = new MyListViewAdpater(getApplicationContext(),restaurants);
        this.listView.setAdapter(myListViewAdpater);

        addRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                Restaurant restaurant1 = new Restaurant();
                restaurant1.setName(name);
                restaurant1.setId("232323");
                restaurants.add(restaurant1);
                myListViewAdpater.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = restaurants.get(position);
                startActivity(restaurant.getName());
            }
        });

        this.connectToServer();
        getCityWeatherViaApi();


    }


    /**
    * lancer une activité
    */

    private void startActivity(String restaurantName ){
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("restName",restaurantName);
        startActivity(intent);
    }


    /** connexion avec le web service
     *
     */
    private void connectToServer (){
       Gson gson = new GsonBuilder()
               .setLenient()
               .create();

       retrofit = new Retrofit.Builder().baseUrl("https://samples.openweathermap.org/data/2.5/")
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();

       weatherApi = retrofit.create(WeatherApi.class);

    }

    /**
     * methode pour get un CityWeather
     */
    private void getCityWeatherViaApi (){
        weatherApi.getJsonObjectData().enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                Log.d(TAG,"onResponse");

                CityWeather cityWeather = response.body();
                if (cityWeather != null){
                    Log.d(TAG,"reçu"+cityWeather.getName());
                }else{
                    Log.d(TAG,"received : weather empty");
                }



            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Log.e("MainActivity ", "  error "+ t.toString());
            }
        });
    }

}

