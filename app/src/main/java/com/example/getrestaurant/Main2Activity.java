package com.example.getrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        String restaurantName = getIntent().getStringExtra("restName");
        TextView textView = (TextView) findViewById(R.id.restName);
        textView.setText(restaurantName);
    }
}
