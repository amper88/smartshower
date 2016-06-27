package com.amper.smartshower.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.amper.smartshower.util.GlobalConfigurationSingleton;

/**
 * Created by amper on 17/06/16.
 */
public class RiegosLocationListener implements LocationListener{

    private final Context appContext;
    private static RiegosLocationListener listenerInstance;

    public static RiegosLocationListener getInstance() {
        return listenerInstance;
    }

    public static RiegosLocationListener createInstance(Context context) {
        listenerInstance = new RiegosLocationListener(context);
        return listenerInstance;
    }

    private RiegosLocationListener(Context context) {
        appContext = context;
    }

    
  public void onLocationChanged(Location loc) {
            loc.getLatitude();
            loc.getLongitude();
            double latitud = GlobalConfigurationSingleton.getInstance().getLatitud();
            double longitud= GlobalConfigurationSingleton.getInstance().getLongitud();;
            double distancia = GlobalConfigurationSingleton.getInstance().getDistancia();
            double distanciacalc = 0;

            //aca habria que consumir las coordenadas de la placa
            Location Locationplaca = new Location( "Location Placa");


            //asigno coordenadas de la placa al objeto location
            Locationplaca.setLatitude(latitud);
            Locationplaca.setLongitude(longitud);


            //Calculo la distancia en metros
            distanciacalc=loc.distanceTo(Locationplaca);

            //Toast.makeText(getApplicationContext(), "Mis coordenadas son: " + "Latitud = " + loc.getLatitude() + "Longitud = " + loc.getLongitude() +" Distancia: "+String.format("%.5f",distancia*1000)+"mts.",Toast.LENGTH_LONG).show();
            //muestro en pantalla mis coordenadas y la distancia

           if(distanciacalc <= distancia && fuelanzada==0){
          //-3459908042
          //-58.55627303
             fuelanzada=1;
               /*ActivityManager am = (ActivityManager)appContext.getSystemService(Context.ACTIVITY_SERVICE);


               ComponentName cn = am.getRunningTasks(1).get(0).topActivity;

                Log.d("onLocationChanged","Valor que trae el running task:"+cn.getClassName());
               if(!cn.getClassName().equals("com.amper.smartshower.GraficoActivity")) {
                   Intent i = new Intent(this.appContext, GraficoActivity.class);
                   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   appContext.startActivity(i);
                   Log.d("onLocationChanged", "lanza activity");
               }*/
               Intent i = new Intent(this.appContext, GraficoActivity.class);
               i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               appContext.startActivity(i);
               Log.d("onLocationChanged", "lanza Grafico");
           }
            Toast.makeText(this.appContext, "Mis coordenadas son: " + "Latitud = " + loc.getLatitude() + "Longitud = " + loc.getLongitude() +" Distancia: "+distanciacalc+"mts.",Toast.LENGTH_LONG).show();
            Log.d("onLocationChanged", "MOVIENDOOOO " + "Mis coordenadas son: " + "Latitud = " + loc.getLatitude() + "Longitud = " + loc.getLongitude() +" Distancia: "+distancia+"mts.");


        }

   
        public void onProviderDisabled(String provider) {
            Toast.makeText(this.appContext, "Gps Desactivado", Toast.LENGTH_SHORT).show();
        }

        public void onProviderEnabled(String provider) {
            Toast.makeText(this.appContext, "Gps Activo", Toast.LENGTH_SHORT).show();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("onLocationChanged", "CAMBIO ESTADO!");
        }


    }
