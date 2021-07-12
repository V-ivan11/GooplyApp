package com.seriouscorp.gooply.Vista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.seriouscorp.gooply.R;

import static com.seriouscorp.gooply.Presentador.UtilidadesVarias.irActividad;
import static com.seriouscorp.gooply.Presentador.UtilidadesVarias.irInicio;

public class Inicio extends Fragment {

    Button b1, b2, b3, b4;
    ImageView covid;
    Boolean alfa = false;
    Class datosCovidClass = DatosCovid.class;
    Class sitiosConfiablesClass = SitiosConfiables.class;
    Class gooplyClass = InfoGooply.class;
    Class companyClass = InfoCompany.class;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        covid = view.findViewById(R.id.imageView11);
        b1 = (Button) view.findViewById(R.id.hob1);
        b2 = (Button) view.findViewById(R.id.hob2);
        b3 = (Button) view.findViewById(R.id.hob3);
        b4 = (Button) view.findViewById(R.id.hob4);

        covid.animate().rotation(1000f).setDuration(100000);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irActividad(getContext(), datosCovidClass);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irActividad(getContext(), sitiosConfiablesClass);
                alfa = true;
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irActividad(getContext(), gooplyClass);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irActividad(getContext(), companyClass);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        if (alfa){
            irInicio(getContext());
            alfa = false;
        }
        super.onResume();
    }

}