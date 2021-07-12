package com.seriouscorp.gooply.Vista;

import android.os.Bundle;

import com.seriouscorp.gooply.R;


import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;


public class DatosCovid extends MaterialIntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SlideFragmentBuilder()
            .backgroundColor(R.color.rojo)
            .buttonsColor(R.color.naranja)
            .image(R.drawable.covid)
            .title("¿Qué es la enfermedad COVID-19?")
            .description("\nEs una enfermedad infecciosa causada por el coronavirus SARS-COV2 que se ha descubierto recientemente. Tanto este nuevo virus como la enfermedad que provoca eran desconocidos antes de que estallara el brote en Wuhan (China) en diciembre de 2019. Actualmente la COVID‑19 es una pandemia que afecta a muchos países de todo el mundo.")
        .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.rojo)
                .buttonsColor(R.color.naranja)
                .image(R.drawable.sintomas)
                .title("Sintomas del la enfermedad COVID-19")
                .description("\nSi perteneces a alguno de los grupos de mayor riesgo para complicarse, como las personas de 60 años y más, personas que viven con enfermedades como hipertensión o diabetes, las mujeres embarazadas, menores de cinco años y personas que viven con cáncer o VIH\n" +
                        "DEBES ACUDIR A RECIBIR ATENCIÓN MÉDICA")
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.rojo)
                .buttonsColor(R.color.naranja)
                .image(R.drawable.trasnmision)
                .title("¿Cómo se transmite?")
                .description("\nLos coronavirus humanos se transmiten de una persona infectada a otras:\n" +
                        "\n" +
                        "• A través de las gotículas que expulsa un enfermo al toser y estornudar\n\n" +
                        "• Al tocar o estrechar la mano de una persona enferma,\n" +
                        "• Un objeto o superficie contaminada con el virus y luego llevarse las manos sucias a boca, nariz u ojos.")
                .build());

    }


}