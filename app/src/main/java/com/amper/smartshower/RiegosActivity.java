package com.amper.smartshower;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amper.smartshower.rest.RestUtil;
import com.amper.smartshower.rest.Riego;
import com.amper.smartshower.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RiegosActivity extends Activity {

    private ListView riegosListView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riegos);
        riegosListView = (ListView) findViewById(R.id.riegos_list);
    }


    public void obtenerRiegos(View v){
        try {
            Log.d("obtenerRiegos", "SOY OBTENER RIEGOS");
            List riegos = doObtenerRiegos();

            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Util.getStringArray(riegos));
            riegosListView.setAdapter(arrayAdapter);

        }catch (Exception e){
            Log.d("obtenerRiegos", e.getMessage());
        }
    }

    private List<Riego> doObtenerRiegos() throws Exception{
        String data = RestUtil.getJSON(Util.URL_GET_RIEGOS, 3000);

        Log.d("obtenerRiegos", "TERMINE GET JSON");

        if(data == null){
            throw new Exception("Request Failed");
        }

        try {
            List riegosList = new ArrayList();
            Log.d("obtenerRiegos", "DATA:"+data.trim());

            JSONObject jRiegos = new JSONObject(data.trim());
            JSONArray jRiegosList =  (JSONArray)jRiegos.get(Util.KEY_RIEGOS_LIST);

            int totalRiegos = jRiegosList.length(); // get totalCount of all jsonObjects

            for(int i=0 ; i< totalRiegos; i++){   // iterate through jsonArray
                JSONObject jRiego = jRiegosList.getJSONObject(i);

                if(!Riego.esRegando(jRiego.get(Riego.COL_ACTION).toString())) continue;

                Riego r = new Riego(String.valueOf(i), jRiego.get(Riego.COL_FECHA_RIEGO).toString());
                riegosList.add(r);
            }

            return riegosList;
        } catch(JSONException ex){
            throw new Exception(ex);
        }
    }

}