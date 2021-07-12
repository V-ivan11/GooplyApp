package com.seriouscorp.gooply.Presentador;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seriouscorp.gooply.Vista.EditarPerfil;

import java.util.HashMap;
import java.util.Map;

public class BDFirebase {

    static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    static DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    public static void nvoevento(String[] datos){
        Map<String, Object> eventosAgendaMap = new HashMap();
        eventosAgendaMap.put("nombreevento", datos[0]);
        eventosAgendaMap.put("tipoevento", datos[1]);
        eventosAgendaMap.put("fecha", "Fecha: " +datos[2]);

        reference.child("Eventos").child(user.getUid()).push().setValue(eventosAgendaMap);
    }

    public static void edusuario(String[] datos){
        Map<String, Object> datosusuario = new HashMap();
        String[] raws = {"usunombre", "mail", "sexo","fecha", "edad","peso","altura","sangre"};
        for (int i = 0; i < raws.length; i++)
            datosusuario.put(raws[i], datos[i]);
        reference.child("Usuarios").child(user.getUid()).setValue(datosusuario);
    }

    public static void agregarlocacion(){
        String[] datos = {"19.338952","-99.0598887","Lidia Lozano","Odontologo","5516033318"};
        Map<String, Object> locacion = new HashMap();
        String[] raws = {"latitud", "longitud", "nombre", "tipodoc","celular"};
        for (int i = 0; i < raws.length; i++)
            locacion.put(raws[i], datos[i]);
        reference.child("Locaciones").push().setValue(locacion);
    }


}
