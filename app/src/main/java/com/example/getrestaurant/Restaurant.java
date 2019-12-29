package com.example.getrestaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("id")
    @Expose
    public String id;

    public String getName() { return name; }
    public void setName(String name){ this.name = name;}

    public String getId (){ return id;}
    public void setId(String id){this.id = id;}
}