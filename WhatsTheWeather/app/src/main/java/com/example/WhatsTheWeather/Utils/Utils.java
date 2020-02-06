package com.example.WhatsTheWeather.Utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public static String ObjDateToDayStr (Date date) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("dd");
        String day = localDateFormat.format(date);
        return day;
    }

    public static Integer CompareDatetoTodayDateInHour (Date date){

        //get today date at midnight in ts
        Calendar todayCal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        todayCal.set(Calendar.HOUR_OF_DAY, 0);
        todayCal.set(Calendar.MINUTE, 0);
        todayCal.set(Calendar.SECOND, 0);
        todayCal.set(Calendar.MILLISECOND, 0);
        Date todayDate = todayCal.getTime();
        Timestamp todayTs = new Timestamp(todayDate.getTime());

        //compare date object to today midnight to if it's the same day
        Timestamp dateEltTs = new Timestamp(date.getTime());
        long diff = dateEltTs.getTime() - todayTs.getTime();
        int diffSec = (int) diff/1000; //pour long compatible;
        int diffHr = diffSec/3600;

        return diffHr;
    }

    public static String getImage(String icon) {
        return String.format("https://openweathermap.org/img/wn/%s@2x.png",icon);
    }

}
