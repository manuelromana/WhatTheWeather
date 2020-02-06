package com.example.WhatsTheWeather.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.WhatsTheWeather.Models.DaysDisplay;
import com.example.WhatsTheWeather.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDays extends RecyclerView.Adapter<AdapterDays.MyViewHolder> {
    private List<DaysDisplay> dataList;
    private static final String TAG = "testButton";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewDay;
        public TextView textViewHour;
        public  TextView textViewTemp;
        //public ImageView iconView;
        public MyViewHolder(View v) {
            super(v);
            textViewDay = v.findViewById(R.id.textViewDay);
            textViewHour = v.findViewById(R.id.textViewHour);
            textViewTemp = v.findViewById(R.id.textViewTemp);
            //iconView = v.findViewById(R.id.imageView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterDays(ArrayList<DaysDisplay> myDataset) {
        dataList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterDays.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_for_day, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d(TAG,"adapt day");
        holder.textViewDay.setText(dataList.get(position).getDay());
        holder.textViewHour.setText(dataList.get(position).getHour()+"h");
        holder.textViewTemp.setText(dataList.get(position).getTemp()+"°C");



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
