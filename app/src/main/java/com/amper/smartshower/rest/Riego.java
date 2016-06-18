package com.amper.smartshower.rest;

import com.amper.smartshower.util.Util;

import java.util.Date;

/**
 * Created by amper on 28/05/16.
 */
public class Riego implements Comparable<Riego> {
    public static final String COL_ID = "id";
    public static final String COL_CONTENT = "content";
    public static final String COL_FECHA_RIEGO = "time";

    private String id;
    private String content;
    private Date fechaRiego;

    public Riego(){

    }

    public Riego(String id, String content, String fechaRiego){
        this.id = id;
        this.content = content;
        this.fechaRiego = Util.getDate(fechaRiego);
    }

    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }
    public String getFechaRiegoString() {
        return this.fechaRiego.toLocaleString();
    }

    public Date getFechaRiego() {
        return this.fechaRiego;
    }

    public String getAnioMesDiaRiego() {
        return this.getFechaRiegoString().substring(0,10);
    }

    public String toString(){
        return this.id+" "+this.content+" "+this.fechaRiego;
    }

    // Overriding the compareTo method
    public int compareTo(Riego r){
        String añoMesDiaR1 = this.getAnioMesDiaRiego();
        String añoMesDiaR2 = r.getAnioMesDiaRiego();

        return añoMesDiaR1.compareTo(añoMesDiaR2);
    }
/*
    // Overriding the compare method to sort the age
    public int compare(Riego r1, Riego r2){
        return r1.getFechaRiego().compareTo(r2.getFechaRiego());
    }*/
}
