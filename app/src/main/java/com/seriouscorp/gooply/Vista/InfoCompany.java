package com.seriouscorp.gooply.Vista;

import android.os.Bundle;

import com.seriouscorp.gooply.R;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class InfoCompany extends MaterialIntroActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.negro)
                .buttonsColor(R.color.rojo)
                .image(R.drawable.seriouslogo)
                .title("Sobre Serious Corp")
                .description("\nSomos una empresa de software dedicada la solución y discusión de problemas" +
                        "sociales en el mundo urbano. Diseñamos soluciones y estrategias que permitan" +
                        "atacar problemas cotidianos dentro de la sociedad a través del desarrollo de" +
                        "sistemas informáticos y software. En específico, nos importa mejorar la movilidad" +
                        "en la ciudad de México para el bienestar de los ciudadanos.")
                .build());
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.negro)
                .buttonsColor(R.color.rojo)
                .image(R.drawable.seriouslogo)
                .title("Sobre Serious Corp")
                .description("\nEn este proyecto le pusimos gran empeño para desarrollar un sistema amigable al usuario paraque este pueda vigilar su salud de manera adecuada.")
                .build());
    }
}
