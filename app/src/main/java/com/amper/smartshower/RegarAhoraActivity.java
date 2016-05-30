package com.amper.smartshower;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Exception;

import org.json.JSONObject;
import org.json.JSONException;
import java.util.*;

import javax.json.JsonException;


public class RegarAhoraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("NavigationItemSelected", "ASD ");
        System.out.println("HOLA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regar_ahora);
    }


    // stuff
    public void myClickMethod(View v) {

        String data = getJSON("http://192.168.1.33:8080/getState", 3000);

        if(data == null){
            System.out.println("Request Failed");
            return;
        }
        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setText(data, TextView.BufferType.EDITABLE);

        try {
            JSONObject jObject = new JSONObject(data.trim());

            Iterator<?> keys = jObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                //if (jObject.get(key) instanceof JSONObject) {
                    System.out.println(key + " : " +jObject.get(key));
                //}
            }
        } catch(JSONException ex){
            System.out.println(ex);
        }

    }


    public String getJSON(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
           // Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    System.out.println(ex);
                    //Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
