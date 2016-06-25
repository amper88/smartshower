package com.amper.smartshower;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amper.smartshower.rest.RestUtil;
import com.amper.smartshower.rest.Riego;
import com.amper.smartshower.util.DateComparator;
import com.amper.smartshower.util.GlobalConfigurationSingleton;
import com.amper.smartshower.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricalActivity extends Activity {
    protected List<Riego> doObtenerRiegos() throws Exception{
        String data = RestUtil.getJSON(GlobalConfigurationSingleton.getInstance().getUrlGetRiegos(), 3000);

        Log.d("obtenerRiegos", "TERMINE GET JSON EN:"+GlobalConfigurationSingleton.getInstance().getUrlGetRiegos());

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