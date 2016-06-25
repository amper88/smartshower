package com.amper.smartshower.util;

import android.os.Bundle;
import android.os.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by amper on 04/06/16.
 */
public class Util {
    //public static final String URL_GET_RIEGOS  = "https://api.myjson.com/bins/55o3e";
    //public static final String KEY_RIEGOS_LIST = "riegos";
    public static final String URL_GET_RIEGOS  = "http://192.168.0.12:8080/getHistoricalData";
    public static final String URL_START_ACTION  = "http://192.168.0.12:8080/startAction";
    public static final String KEY_RIEGOS_LIST = "content";

    public static Date getDate(String dateStr){
        //String s = "03/24/2013 21:54";
<<<<<<< HEAD
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
=======
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
>>>>>>> develop
        try{
            date = simpleDateFormat.parse(dateStr);
            System.out.println("date : "+simpleDateFormat.format(date));
            return date;
        }
        catch (ParseException ex){
            System.out.println("Exception "+ex);
            return new Date();
        }
    }

    public static String[] getStringArray(List objectList){
        String[] array = new String[objectList.size()];
        int index = 0;
        for (Object value : objectList) {
            array[index] = (String) value.toString();
            index++;
        }
        return array;
    }

    public static Message createHandlerMessage(Message hndMessage, String msg){
        Bundle b = new Bundle();
        b.putString("message", msg);
        hndMessage.setData(b);
        return hndMessage;
    }


}
