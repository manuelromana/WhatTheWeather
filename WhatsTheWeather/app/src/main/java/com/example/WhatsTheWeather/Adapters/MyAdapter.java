
    package com.example.WhatsTheWeather.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.WhatsTheWeather.Models.ForecastDisplay;
import com.example.WhatsTheWeather.Models.ListForecast;
import com.example.WhatsTheWeather.Models.WheatherForecast;
import com.example.WhatsTheWeather.R;

import java.util.ArrayList;
import java.util.List;

    public class MyAdapter extends RecyclerView.Adapter<com.example.WhatsTheWeather.Adapters.MyAdapter.MyViewHolder> {
        //private String[] mDataset;
        //private List<String> listData ;
        private List<ForecastDisplay> listData ;
        private static final String TAG = "testButton";


        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView textView;
            public TextView textView2;
            public TextView textViewCelcius;
            public MyViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.textView2);
                textView2 = v.findViewById(R.id.textView3);
                textViewCelcius = v.findViewById(R.id.textViewCelcius);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<ForecastDisplay> myDataset) {
           listData = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public com.example.WhatsTheWeather.Adapters.MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                              int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.forcast_test, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            /*if(listData.size() == 0) {
                Log.d(TAG, "list0" );
                holder.textView.setText("loading");
                return;
            }*/

            holder.textView.setText("°F : "+listData.get(position).getTemp());
            holder.textViewCelcius.setText("°C : "+listData.get(position).getTemp_celsius());
            holder.textView2.setText(listData.get(position).getDate()+"h"); //change to get Hour

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            if(listData == null){
                return 1;
            }
            return listData.size();
        }
    }

