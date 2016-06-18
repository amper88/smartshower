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
import com.amper.smartshower.util.Grafico;
import com.amper.smartshower.util.Util;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
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
            List<Riego> riegos = doObtenerRiegos();

            /*
            List<Riego> riegos = new ArrayList<Riego>();

            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.22 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.22 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.25 13.07.11")));
            */

            Collections.sort(riegos,new DateComparator());

            //Creamos la grafica vacia
            BarChart grafica = new BarChart(getApplicationContext());
            setContentView(grafica);

            Grafico grafico = new Grafico(riegos, grafica);

            //Cargar la grafica y mostrarla
            grafico.drawBarChart(grafico.getBarChart());

            //Se comenta para mostrar la barra en vez del listView
//            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Util.getStringArray(riegos));
//            riegosListView.setAdapter(arrayAdapter);

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

            Log.d("obtenerRiegos", "A");
            for(int i=0 ; i< totalRiegos; i++){   // iterate through jsonArray
                Log.d("obtenerRiegos", ""+i);
                JSONObject jRiego = jRiegosList.getJSONObject(i);  // get jsonObject @ i position
                riegosList.add(new Riego(jRiego.get(Riego.COL_ID).toString(), jRiego.get(Riego.COL_CONTENT).toString(), jRiego.get(Riego.COL_FECHA_RIEGO).toString()));
            }

            Log.d("obtenerRiegos", "B");
            return riegosList;
        } catch(JSONException ex){
            throw new Exception(ex);
        }
    }

}