package com.seriouscorp.gooply.Vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seriouscorp.gooply.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.seriouscorp.gooply.Presentador.BDFirebase.edusuario;

public class EditarPerfil extends AppCompatActivity {

    EditText ededad, edpeso, edaltura;
    Spinner spsexo, spsangre;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Usuarios").child(user.getUid());
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        ededad = (EditText) findViewById(R.id.etedad);
        edpeso = (EditText) findViewById(R.id.edpeso);
        edaltura = (EditText) findViewById(R.id.edaltura);
        spsexo = (Spinner) findViewById(R.id.sexo);
        spsangre = (Spinner) findViewById(R.id.sangre);

        referencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[] datos = new String[8];
                int i =0;
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        datos[i] =snapshot.getValue().toString();
                        i ++;
                    }
                    ededad.setText(datos[1]);
                    edpeso.setText(datos[4]);
                    edaltura.setText(datos[0]);

                }else
                    Toast.makeText(EditarPerfil.this, "Registra tus datos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayAdapter<CharSequence> adaptersexo = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adaptersangre = ArrayAdapter.createFromResource(this, R.array.sangre, android.R.layout.simple_spinner_item);
        adaptersexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptersangre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spsangre.setAdapter(adaptersangre);
        spsexo.setAdapter(adaptersexo);
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        date = df.format(c);

    }

    public void regresar(View view) {
        onBackPressed();
    }

    public void editarp(View view) {
        if (llenos()) {
            String[] datos = new String[8];
            datos[0] = user.getDisplayName();
            datos[1] = user.getEmail();
            datos[2] = spsexo.getSelectedItem().toString();
            datos[3] = date;
            datos[4] = ededad.getText().toString();
            datos[5] = edpeso.getText().toString();
            datos[6] = edaltura.getText().toString();
            datos[7] = spsangre.getSelectedItem().toString();
            edusuario(datos);
            onBackPressed();
        } else
            Toast.makeText(this, "Llena todos tus campos", Toast.LENGTH_SHORT).show();
    }

    public boolean llenos() {
        if (spsexo.getSelectedItem().toString().equals("Seleccione su Sexo") || ededad.getText().toString().equals("") || edaltura.getText().toString().equals("") || spsangre.getSelectedItem().toString().equals("Seleccione tipo de Sangre"))
            return false;
        else
            return true;
    }
}