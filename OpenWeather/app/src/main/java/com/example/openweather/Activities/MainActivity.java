package com.example.openweather.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.openweather.R;


public class MainActivity extends AppCompatActivity {


    private Button getMeteoButton;
    private EditText searchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getMeteoButton = (Button) findViewById(R.id.GetData);
        this.searchText = (EditText) findViewById(R.id.CityName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getMeteoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchText.getText().toString().trim();
                startActivityGetMeteo(cityName);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    private void startActivityGetMeteo(String cityName ){
        Intent intent = new Intent(this, GetMeteo.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }
}
