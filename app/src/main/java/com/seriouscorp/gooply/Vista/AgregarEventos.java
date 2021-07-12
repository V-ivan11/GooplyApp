package com.seriouscorp.gooply.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.work.Data;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.seriouscorp.gooply.R;
import com.seriouscorp.gooply.Presentador.Workmanagex;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import static com.seriouscorp.gooply.Presentador.BDFirebase.nvoevento;

public class AgregarEventos extends AppCompatActivity {

    TextView tfecha, thora;
    Spinner spinner;
    EditText recordatorio, numrep;
    Switch aSwitch;
    CardView cardView;
    Boolean repeticion = false;
    String tipo, numr;
    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();
    Calendar[] semodia;


    private int minutos, hora, dia, mes, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_eventos);

        tfecha = findViewById(R.id.gdia);
        thora = findViewById(R.id.ghora);
        spinner = findViewById(R.id.spinner);
        recordatorio = findViewById(R.id.trecordatorio);
        numrep = findViewById(R.id.numrep);
        aSwitch =(Switch) findViewById(R.id.swrep);
        cardView = findViewById(R.id.cardView3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.vecesrep, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    numrep.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                    repeticion = true;
                }else{
                    numrep.setVisibility(View.INVISIBLE);
                    cardView.setVisibility(View.INVISIBLE);
                    repeticion = false;
                }
            }
        });

    }

    public void regresar(View view) {
        onBackPressed();
    }

    public void guardar(View view) {
        if(repeticion){
            numr = numrep.getText().toString();
            semodia = new Calendar[Integer.parseInt(numr)];
            tipo = spinner.getSelectedItem().toString();

            if(repeticion){
                if(tipo.equals("Semanalmente")){
                    for (int i = 0; i < semodia.length; i++) {
                        semodia[i] = Calendar.getInstance();
                        semodia[i].set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                        semodia[i].set(Calendar.MONTH,(calendar.get(Calendar.MONTH)+i));
                        semodia[i].set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
                    }
                }else
                if (tipo.equals("Diariamente")){
                    for (int i = 0; i < semodia.length; i++) {
                        semodia[i] = Calendar.getInstance();
                        semodia[i].set(Calendar.YEAR,calendar.get(Calendar.YEAR));
                        semodia[i].set(Calendar.MONTH,calendar.get(Calendar.MONTH));
                        semodia[i].set(Calendar.DAY_OF_MONTH,(calendar.get(Calendar.DAY_OF_MONTH)+i));
                    }
                }
            }

            for (int i = 0; i < Integer.parseInt(numr); i++) {
                String tag = generatekey();
                Long alerta = semodia[i].getTimeInMillis() - System.currentTimeMillis();
                int random = (int)(Math.random());
                Data data = GuardarData(recordatorio.getText().toString(), recordatorio.getText().toString(), random);
                Workmanagex.guardarNoti(alerta, data, generatekey());
                String[] datos = new String[3];
                datos[0] = recordatorio.getText().toString();
                datos[1] = "otro";
                datos[2] = tfecha.getText().toString()+" "+thora.getText().toString();
                nvoevento(datos);
            }

        }else {
            String tag = generatekey();
            Long alerta = calendar.getTimeInMillis() - System.currentTimeMillis();
            int random = (int)(Math.random());
            Data data = GuardarData(recordatorio.getText().toString(), recordatorio.getText().toString(), random);
            Workmanagex.guardarNoti(alerta, data, generatekey());
            String[] datos = new String[3];
            datos[0] = recordatorio.getText().toString();
            datos[1] = "unico";
            datos[2] = tfecha.getText().toString()+" "+thora.getText().toString();
            nvoevento(datos);
        }

        Toast.makeText(this, "Guardada", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    public void selecthora(View view) {
        year = actual.get(Calendar.YEAR);
        mes = actual.get(Calendar.MONTH);
        dia = actual.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                calendar.set(Calendar.YEAR,y);
                calendar.set(Calendar.MONTH,m);
                calendar.set(Calendar.DAY_OF_MONTH,d);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                tfecha.setText(format.format(calendar.getTime()));

            }
        },year, mes, dia);
        datePickerDialog.show();

    }

    public void selectfecha(View view) {
        hora = actual.get(Calendar.HOUR_OF_DAY);
        minutos = actual.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                calendar.set(Calendar.HOUR_OF_DAY,h);
                calendar.set(Calendar.MINUTE,m);

                thora.setText(String.format("%02d:%02d", h, m));

            }
        },hora,minutos,true);
        timePickerDialog.show();
    }


    private Data GuardarData(String titulo, String detalle, int id_noti){
        return new Data.Builder()
                .putString("titulo", titulo)
                .putString("detalle", detalle)
                .putInt("idnoti", id_noti).build();
    }

    private String generatekey(){
        return UUID.randomUUID().toString();
    }

}