package com.amper.smartshower.util;

/**
 * Created by amper on 25/06/16.
 */
public class GlobalConfigurationSingleton {
    private static GlobalConfigurationSingleton instance;

    // Global variables
    private String urlGetRiegos;
    private String urlStartAction;

    private double latitud;
    private double longitud;
    private double distancia;

    // Set default Riegos values and restrict the constructor from being instantiated
    private GlobalConfigurationSingleton(){
        this.urlGetRiegos    = "http://192.168.0.12:8080/getHistoricalData";
        this.urlStartAction  = "http://192.168.0.12:8080/startAction";

        this.latitud   = -34.5992234;
        this.longitud  = -58.5563815;
        this.distancia = 0;
    }

    // Getters & Setters
    public void setUrlGetRiegos(String d){
        this.urlGetRiegos=d;
    }
    public String getUrlGetRiegos(){
        return this.urlGetRiegos;
    }
    public void setUrlStartAction(String d){
        this.urlStartAction=d;
    }
    public String getUrlStartAction(){
        return this.urlStartAction;
    }
    public void setLatitud(double d){
        this.latitud=d;
    }
    public double getLatitud(){
        return this.latitud;
    }
    public void setLongitud(double d){
        this.longitud=d;
    }
    public double getLongitud(){
        return this.longitud;
    }
    public void setDistancia(double d){
        this.distancia=d;
    }
    public double getDistancia(){
        return this.distancia;
    }


    // GetInstance
    public static synchronized GlobalConfigurationSingleton getInstance(){
        if(instance==null){
            instance = new GlobalConfigurationSingleton();
        }
        return instance;
    }
}
