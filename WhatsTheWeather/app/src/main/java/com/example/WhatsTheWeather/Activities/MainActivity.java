package com.example.WhatsTheWeather.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.WhatsTheWeather.Models.FullWeather;
import com.example.WhatsTheWeather.Network.RetrofitClientInstance;
import com.example.WhatsTheWeather.Network.WeatherService;
import com.example.WhatsTheWeather.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


private static final String TAG = "meteoactivity";
static Double lat,lng;
private final int REQUEST_PERMISSION_CODE =1;
private LocationManager locationManager;
private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initlocation();
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);






    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_get_meteo:
                startActivityGetMeteo("test");
                return true;
            case R.id.action_search:
    startActivitySearch();
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    private void startActivityGetMeteo(String cityName ){
        Intent intent = new Intent(this, GetMeteo.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }
    private void startActivitySearch( ){
        Intent intent = new Intent(this, search.class);

        startActivity(intent);
    }

    //location
    private void initlocation (){

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lng = location.getLongitude();
                Log.d(TAG,"toto");
                getInfosToday(lat.toString(),lng.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast.makeText(getApplicationContext(),"Provider"+" "+status,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(String provider) {
         Log.d(TAG,"onProviderEnable"+provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d(TAG,"onproviderDisable"+provider);
            }
        };
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                        //afficher un message pour expliquer l'autorisation de la géoloc
                        new AlertDialog.Builder(this)
                                .setTitle("permission geolocalisation")
                                .setMessage("besoin de l'autorisation pour la fonctionnalité")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION_CODE);
                                    }
                                })
                                .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       dialog.dismiss();
                                    }
                                })
                        .create().show();
                    }else{
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION_CODE);

                    }


                } else{
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,10f,locationListener);
                }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_PERMISSION_CODE){
            if(grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                initlocation();
            } else {
                Log.d(TAG,"no permission");
            }
        }
    }

    /*private Location getLastLocation(){
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }*/


    private void getInfosToday(String lng,String lat) {


        WeatherService service = RetrofitClientInstance.getRetrofitInstance().create(WeatherService.class);
        Call<FullWeather> callAsync = service.getFullWeatherWithCoord(lat,lng, "cb9b73946c744e86f5c069c97e42af61","metric");

        callAsync.enqueue(new Callback<FullWeather>() {
            @Override
            public void onResponse(Call<FullWeather> call, Response<FullWeather> response) {

                if (response.body() == null) {
                    Log.d(TAG, "onResponseQueryNull: " + "veuillez entrer une ville");
                    Log.d(TAG, "onResponseQueryNull: " + response.raw());

                    return;

                }
                FullWeather fullWeather = response.body();


                if (fullWeather != null) {

                    Double tempC = fullWeather.getMain().getTemp();
                    Double tempF = tempC*1.8 +32;

                    Log.d(TAG, "onResponse: " );

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
