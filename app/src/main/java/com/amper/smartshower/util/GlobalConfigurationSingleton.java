package com.amper.smartshower.util;

/**
 * Created by amper on 25/06/16.
 */
public class GlobalConfigurationSingleton {
    private static GlobalConfigurationSingleton instance;

    // Global variables
    private String urlGetRiegos;
    private String urlStartAction;

    // Set default Riegos values and restrict the constructor from being instantiated
    private GlobalConfigurationSingleton(){
        this.urlGetRiegos    = "http://192.168.0.12:8080/getHistoricalData";
        this.urlStartAction  = "http://192.168.0.12:8080/startAction";
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

    // GetInstance
    public static synchronized GlobalConfigurationSingleton getInstance(){
        if(instance==null){
            instance = new GlobalConfigurationSingleton();
        }
        return instance;
    }
}
