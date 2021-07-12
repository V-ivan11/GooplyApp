package com.seriouscorp.gooply.Vista;

import android.os.Bundle;
import android.view.View;

import com.seriouscorp.gooply.R;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.MessageButtonBehaviour;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

import static com.seriouscorp.gooply.Presentador.UtilidadesVarias.abrirWeb;

public class SitiosConfiables extends MaterialIntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimaryDark)
                .buttonsColor(R.color.colorPrimary)
                .image(R.drawable.fuentesde)
                .title("Recuerda siempre consultar fuentes confiables")
                .description("\nLa información disponible en la Web no está regulada de acuerdo a su calidad  o veracidad y si se trata sobre tu salud siempre es de suma importancia que consultes la información de las fuentes más confiables, a continuación te dejamos 2 sitios web totalmente confiables, aún así a cualquier duda consulte a su médico.")
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimaryDark)
                .buttonsColor(R.color.colorPrimary)
                .image(R.drawable.gobierno)
                .title("www.gob.mx/salud")
                .description("\nPágina ofcial de la Secretaria de Salud del Gobierno de México.")
                .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        abrirWeb(getApplicationContext(),"https://www.gob.mx/salud");
                    }
                },"Ir a gob.mx/salud"
        ));

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryDark)
                        .buttonsColor(R.color.colorPrimary)
                        .image(R.drawable.oms)
                        .title("www.who.int/es")
                        .description("\nPágina ofcial de la Organización Mundial de la Salud en español.")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        abrirWeb(getApplicationContext(),"https://www.who.int/es");
                    }
                },"Ir a who.int/es"
                ));
    }
}
