package com.example.openweather.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.openweather.R;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {


    private Button getMeteoButton;
    private EditText searchText;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getMeteoButton = (Button) findViewById(R.id.GetData);
        this.searchText = (EditText) findViewById(R.id.CityName);
        this.image = (ImageView) findViewById(R.id.imageView2);

        String imageUri = "https://openweathermap.org/img/wn/10d@2x.png";

        Picasso.with(this).load(imageUri).into(image);

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
