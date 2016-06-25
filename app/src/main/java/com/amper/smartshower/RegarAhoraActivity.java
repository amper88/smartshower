package com.amper.smartshower;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;

import android.util.Log;

import com.amper.smartshower.rest.RestUtil;
import com.amper.smartshower.rest.Riego;
import com.amper.smartshower.util.Util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Exception;

import org.json.JSONArray;
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

        this.doRegarAhora();
    }

    // stuff
    public void doRegarAhora() {
        TextView t = (TextView)findViewById(R.id.txtRegando);
        t.setText("¡¡REGANDO!!");

        WebView wv = (WebView)findViewById(R.id.webViewRegando);
        wv.setBackgroundColor(Color.TRANSPARENT);
        wv.loadUrl("file:///android_asset/regando.gif");


        String data = RestUtil.getJSON(Util.URL_START_ACTION, 3000);
        Log.d("doRegarAhora", "TERMINE GET JSON");
        if(data == null){
            Log.d("doRegarAhora", "REQUEST FAILED");
        }
    }

    public void regarAhoraAction(View v) {
        this.doRegarAhora();
    }

}
