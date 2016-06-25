package com.amper.smartshower;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amper.smartshower.rest.Riego;
import com.amper.smartshower.util.DateComparator;
import com.amper.smartshower.util.Util;

import java.util.Collections;
import java.util.List;

public class RiegosActivity extends HistoricalActivity {

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

            Collections.sort(riegos,new DateComparator());

            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Util.getStringArray(riegos));
            riegosListView.setAdapter(arrayAdapter);

        }catch (Exception e){
            Log.d("obtenerRiegos", e.getMessage());
        }
    }
}