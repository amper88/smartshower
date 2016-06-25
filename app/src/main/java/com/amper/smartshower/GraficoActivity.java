package com.amper.smartshower;

import android.os.Bundle;
import android.view.WindowManager;

import com.amper.smartshower.rest.Riego;
import com.amper.smartshower.util.DateComparator;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraficoActivity extends HistoricalActivity {

    protected BarChart grafica;
    private List<Riego> riegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                 WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_grafico);

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


    public BarData getData(){
        try {
  /*        riegos = new ArrayList<Riego>();

            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.25 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.22 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.23 13.07.11")));
            riegos.add(new Riego(new String("1"),new String("Medicion 1"),new String( "2016.05.25 13.07.11")));
*/


                riegos = doObtenerRiegos();

                Collections.sort(riegos,new DateComparator());

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
        grafica.setDrawGridBackground(false);

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