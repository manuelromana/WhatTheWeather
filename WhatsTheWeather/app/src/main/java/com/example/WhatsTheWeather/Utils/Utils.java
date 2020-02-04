package com.example.WhatsTheWeather.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date StrDateToDateObj(String stringDate)throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateformat = dateFormatter.parse(stringDate);
        return dateformat;

    }

    public static String ObjDateToTimeStr (Date date) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH");
        String time = localDateFormat.format(date);
        return time;
    }
}
