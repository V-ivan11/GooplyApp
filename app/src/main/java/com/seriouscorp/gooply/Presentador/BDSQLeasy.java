package com.seriouscorp.gooply.Presentador;

import android.database.Cursor;

import org.richit.easiestsqllib.EasiestDB;

public class BDSQLeasy {


    public static String getFase(EasiestDB db){
        String pase = null;
        Cursor cursor = db.getAllDataOrderedBy(0, false,0);
        if(cursor != null){
            while (cursor.moveToNext()){
                pase = cursor.getString(1);
            }
        }
        return pase;
    }


}
