package com.seriouscorp.gooply.Presentador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.seriouscorp.gooply.Vista.IniciarSesion;

public class UtilidadesVarias {

    public static void irActividad(Context context, Class classClass) {
        Intent intent = new Intent(context,classClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void irInicio(Context context) {
        Intent intent = new Intent(context, IniciarSesion.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void marcar(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void abrirWeb(Context context, String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

}
