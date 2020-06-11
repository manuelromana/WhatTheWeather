package com.example.WhatsTheWeather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.WhatsTheWeather.Models.FullWeather;
import com.example.WhatsTheWeather.Models.ListForecast;
import com.example.WhatsTheWeather.Models.WeatherInfos;
import com.example.WhatsTheWeather.Models.WheatherForecast;
import com.example.WhatsTheWeather.Network.RetrofitClientInstance;
import com.example.WhatsTheWeather.Network.WeatherService;
import com.example.WhatsTheWeather.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMeteo extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "LogWeather";
    private String meteo;
    private Button getMeteoButton;
    private EditText searchText;
    private ImageView image;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_meteo);

        /*
         * code pour button action et afficher image pas utilisé pour le moment
         * */

        /*this.getMeteoButton = (Button) findViewById(R.id.GetData);
        this.searchText = (EditText) findViewById(R.id.CityName);
        this.image = (ImageView) findViewById(R.id.imageView2);*/
        /*String imageUri = "https://openweathermap.org/img/wn/10d@2x.png";

        Picasso.with(this).load(imageUri).into(image);*/

        /*getMeteoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchText.getText().toString().trim();
                startActivityGetMeteo(cityName);

            }
        });*/
        //String cityName = getIntent().getStringExtra("cityName");

        SearchView searchView = (SearchView) findViewById(R.id.simple_search); // inititate a search view


        searchView.setOnQueryTextListener(this);


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        getDatasWithRetrofit(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }



   /* public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                Log.d(TAG, "toto");
                return true;




            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }*/

    private void getDatasWithRetrofit(String cityName) {


        WeatherService service = RetrofitClientInstance.getRetrofitInstance().create(WeatherService.class);
        Call<ListForecast> callAsync = service.getForecast(cityName, "cb9b73946c744e86f5c069c97e42af61","metric");

        callAsync.enqueue(new Callback<ListForecast>() {
            @Override
            public void onResponse(Call<ListForecast> call, Response<ListForecast> response) {

                if (response.body() == null) {
                    Log.d(TAG, "onResponseQueryNull: " + "veuillez entrer une ville");
                    return;

                }
                ListForecast responseList = response.body();


                if (responseList != null) {


                    Log.d(TAG, "onResponse: " + responseList.getWeather().get(2).getMain().getTemp());
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

    }


}
