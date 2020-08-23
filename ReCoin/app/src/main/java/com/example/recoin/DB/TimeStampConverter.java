package com.example.recoin.DB;

import androidx.room.TypeConverter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeStampConverter {
    static DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    @TypeConverter
    public static Long fromString(String value)  {
       try {
           if (value == null) {
               return null;
           } else {

               Date data = (Date) df.parse(value.replace("Z", ""));
               if (data !=null) {
                   String stringData = data.toString();
                   stringData = stringData.replace("-", "");
                   stringData = stringData.replace(":", "");
                   stringData = stringData.replace(" ", "");
                   return Long.valueOf(stringData);
               } else {

                   return null;
               }


           }
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }

    }

    @TypeConverter
    public static String fromDate(Long value) {
        if (value == null) {
            return null;
        } else {             String sData = value.toString();
            String sYear = sData.substring(0,3);
            String sMonth = sData.substring(4,5);
            String sDay = sData.substring(6,7);
            String sHour = sData.substring(8,9);;
            String sMinutes = sData.substring(10,11);
            String sSeconds = sData.substring(12,13);
            sData = sYear + "-" + sMonth + "-" + sDay + " " + sHour + ":" + sMinutes + ":" + sSeconds;
            return sData;



        }
    }







}
