package com.amper.smartshower;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.amper.smartshower.rest.RestUtil;
import com.amper.smartshower.rest.Riego;
import com.amper.smartshower.util.DateComparator;
import com.amper.smartshower.util.Util;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GraficoActivity extends Activity implements OnChartValueSelectedListener {

    protected BarChart grafica;
    private List<Riego> riegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                 WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_grafico);

  /*        riegos = new ArrayList<Riego>();

            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.25 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.22 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.25 13.07.11")));
*/

            //Creamos la grafica vacia
            grafica = new BarChart(this);
            setContentView(grafica);

            //Obtenemos los datos de las mediciones para graficar
            BarData datos = getData();

            //Agregamos los datos al gráfico
            grafica.setData(datos);

            grafica.setDescription("");

            configBarChart();

            grafica.invalidate();

    }


    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {

    }

    @Override
    public void onNothingSelected() {

    }

    public BarData getData(){
        try {

                riegos = doObtenerRiegos();

                //Comparator<Integer> comparador = Collections.reverseOrder();
            //Collections.sort(arrayListInt, comparador);

                Collections.sort(riegos,new DateComparator());
            //Collections.sort(riegos,comparador);

                Riego auxRiego = riegos.get(0);
                int cantMeasurementDay = 0;
                int indexDay = 0;
                ArrayList<BarEntry> entradas = new ArrayList<>();
                ArrayList<String> etiquetas = new ArrayList<String>();

                //Cargamos los datos de los ejes x, y
                for (Riego riego : this.riegos) {

                    if (riego.compareTo(auxRiego) == 0) {
                        cantMeasurementDay++;
                    } else {
                        //Valores a mostrar en la grafica
                        entradas.add(new BarEntry(cantMeasurementDay, indexDay));

                        //Etiquetas para el eje X
                        etiquetas.add(auxRiego.getAnioMesDiaRiego());
                        auxRiego = riego;
                        indexDay++;
                        cantMeasurementDay = 1;
                    }
                }

                entradas.add(new BarEntry(cantMeasurementDay, indexDay));

                //Etiquetas para el eje X
                etiquetas.add(auxRiego.getAnioMesDiaRiego());

                BarDataSet dataset = new BarDataSet(entradas, "# de mediciones");

                //Aplicamos una plantillas de colores al conjunto de datos
                dataset.setColors(ColorTemplate.COLORFUL_COLORS);

                BarData datos = new BarData(etiquetas, dataset);

                return datos;

        } catch (Exception e) {
            e.printStackTrace();
        }
            return new BarData();
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

    public void configBarChart() {

        //Seteo el color de fondo para que se ve al texto
        grafica.setBackgroundColor(ColorTemplate.rgb("#C0C0C0"));
        grafica.isAutoScaleMinMaxEnabled();

        //Aplicamos una animación al eje Y
        grafica.animateXY(5000, 5000);

        // scaling can now only be done on x- and y-axis separately
        grafica.setPinchZoom(true);

        //Seteo el color de fondo para que se ve al texto
        grafica.setBackgroundColor(ColorTemplate.rgb("#C0C0C0"));
        grafica.isAutoScaleMinMaxEnabled();

        //Aplicamos una animación al eje Y
        grafica.animateXY(5000, 5000);

        // scaling can now only be done on x- and y-axis separately
        grafica.setPinchZoom(true);

        //Guardar el gráfico como jpg en la SD
        /*if(this.permissionLoadOk == true) {
            grafica.isSaveEnabled();
            grafica.saveToGallery("riegoExample.jpg", 85);
        }*/
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}