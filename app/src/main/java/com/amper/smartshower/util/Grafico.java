package com.amper.smartshower.util;

import com.amper.smartshower.rest.Riego;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 18/06/16.
 */
public class Grafico {

    private int cantMeasurementDay;
    private int indexDay;
    private List<Riego> riegos;
    private Riego auxRiego;
    private ArrayList<BarEntry> entradas;
    private ArrayList<String> etiquetas;
    private BarChart grafica;

    public Grafico( List<Riego> riegos, BarChart graficBar ) {
        this.cantMeasurementDay = 0;
        this.indexDay = 0;
        this.riegos = riegos;
        auxRiego = riegos.get(0);
        entradas = new ArrayList<>();
        etiquetas = new ArrayList<String>();
        grafica = graficBar;
    }

    public BarChart getBarChart() {

        //Cargamos los datos de los ejes x, y
        for (Riego riego : this.riegos) {

            if (riego.compareTo(this.auxRiego) == 0) {
                this.cantMeasurementDay++;
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

        //Agregamos los datos de las mediciones al grafico
        BarDataSet dataset = new BarDataSet(entradas, "# de mediciones");
        BarData datos = new BarData(etiquetas,dataset);

        grafica.setData(datos);

        grafica.setDescription("");
        grafica.getBottom();

        //Aplicamos una plantillas de colores al conjunto de datos
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        return grafica;
    }

    public void drawBarChart(BarChart grafica) {

        //Seteo el color de fondo para que se ve al texto
        grafica.setBackgroundColor(ColorTemplate.rgb("#C0C0C0"));
        grafica.isAutoScaleMinMaxEnabled();

        //Aplicamos una animación al eje Y
        grafica.animateXY(5000,5000);

        //Guardar el gráfico como jpg en la SD
        //grafica.isSaveEnabled();
        //grafica.saveToGallery("riegoExample.jpg",85);
    }
}
