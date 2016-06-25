package com.amper.smartshower.util;

import com.amper.smartshower.rest.Riego;

import java.util.Comparator;

/**
 * Created by edward on 18/06/16.
 */
public class DateComparator implements Comparator<Riego> {
    @Override
    public int compare(Riego r1, Riego r2){
        String añoMesDiaR1 = r1.getAnioMesDiaRiego();
        String añoMesDiaR2 = r2.getAnioMesDiaRiego();

        //Horrible pero el -1 para que ordene de menor a mayor
        return añoMesDiaR1.compareTo(añoMesDiaR2)*-1;
    }
}
