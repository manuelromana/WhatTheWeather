package com.example.getrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Restaurant> restaurants;
    private Button addRestaurantButton;
    private EditText editText;
    private MyListViewAdpater myListViewAdpater;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurants = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.listview);
        this.addRestaurantButton = (Button) findViewById(R.id.buttonAddRestaurant);
        this.editText = (EditText) findViewById(R.id.et_restname);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Mon restaurant");
        restaurant.setId("4321");
        restaurants.add(restaurant);

        this.myListViewAdpater = new MyListViewAdpater(getApplicationContext(),restaurants);
    this.listView.setAdapter(myListViewAdpater);
    }
}
