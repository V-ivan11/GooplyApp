package com.seriouscorp.gooply.Vista;

import android.os.Bundle;

import com.seriouscorp.gooply.R;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;


public class InfoGooply extends MaterialIntroActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimaryDark)
                .buttonsColor(R.color.rojo)
                .image(R.drawable.logotransparente)
                .title("Gooply")
                .description("\nSer una plataforma de gestión, consulta y comunicación de tratamientos" +
                        "médicos dirigido a médicos y pacientes para llevar un control de cada uno" +
                        "de los pacientes de manera adecuada.")
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimaryDark)
                .buttonsColor(R.color.rojo)
                .image(R.drawable.logotransparente)
                .title("Gooply")
                .description("\n Con una app en la cual el paciente puede guardar sus datos, hacer recordatorios para medicinas o citas médicas y una opción especial si el usuario se encuentra lejos de casa o necesita localizar  a un médico cerca de él, puede localizarlo con google maps. ")
                .build());
    }

}
