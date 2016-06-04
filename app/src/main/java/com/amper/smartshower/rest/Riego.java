package com.amper.smartshower.rest;

import com.amper.smartshower.util.Util;

import java.util.Date;

/**
 * Created by amper on 28/05/16.
 */
public class Riego {
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
    public String getFechaRiego() {
        return this.fechaRiego.toString();
    }

    public String toString(){
        return this.id+" "+this.content+" "+this.fechaRiego;
    }
}
