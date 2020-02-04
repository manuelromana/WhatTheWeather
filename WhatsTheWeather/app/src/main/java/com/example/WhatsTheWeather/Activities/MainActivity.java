package com.example.WhatsTheWeather.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.WhatsTheWeather.R;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {


private static final String TAG = "meteoactivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
