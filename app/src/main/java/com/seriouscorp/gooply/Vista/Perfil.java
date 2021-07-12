package com.seriouscorp.gooply.Vista;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.seriouscorp.gooply.R;
import com.seriouscorp.gooply.Modelo.UsuarioPaciente;

import org.richit.easiestsqllib.Datum;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.seriouscorp.gooply.Vista.IniciarSesion.db;


public class Perfil extends Fragment {

    Button cerrars;
    CircleImageView imagefoto;
    TextView nomUsuario, correo, altura, peso, sangre, edad, sexo, fecha;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public Perfil() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        determinarTextViews(root);

        imagefoto.bringToFront();
        nomUsuario.setText(user.getDisplayName());
        correo.setText(user.getEmail());

        try {
            if(!user.getPhotoUrl().equals(null))
                Glide.with(getContext()).load(user.getPhotoUrl()).into(imagefoto);
        }catch (Exception e){}


        cerrars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = null;
                AuthUI.getInstance().signOut(getContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                regreso();
                            }
                        });
            }
        });
        return root;
    }

    private void determinarTextViews(View root) {
        cerrars = (Button)root.findViewById(R.id.cerrars);
        imagefoto = (CircleImageView)root.findViewById(R.id.imagenperfil);
        nomUsuario = (TextView)root.findViewById(R.id.nomusuario);
        correo = (TextView)root.findViewById(R.id.tcorreo);
        altura = (TextView)root.findViewById(R.id.tvaltura);
        peso = (TextView)root.findViewById(R.id.tvpeso);
        edad = (TextView)root.findViewById(R.id.tvedad);
        sexo = (TextView)root.findViewById(R.id.tvsexo);
        sangre = (TextView)root.findViewById(R.id.tvsangre);
        fecha = (TextView)root.findViewById(R.id.tvfecha);

        nomUsuario.setText("");
        correo.setText("");
        altura.setText("");
        peso.setText("");
        edad.setText("");
        sexo.setText("");
        sangre.setText("");
        fecha.setText("");
    }

    private void actualiza() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(user.getUid());
        final TextView[] textViews = {altura,edad,fecha,correo,peso,sangre, sexo,nomUsuario};
        reference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                return null;
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSapshot) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            int i = 0;
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                textViews[i].setText(snapshot.getValue().toString());
                                i = i + 1;
                            }
                            fecha.setText("Última actualización: " + fecha.getText().toString());
                            edad.setText("Edad: " + edad.getText().toString()+" años");
                            peso.setText(peso.getText().toString() + " kg");
                            altura.setText(altura.getText().toString()+ "cm");
                        }else {
                            Toast.makeText(getContext(), "Llena tus datos", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    private void regreso() {
        Intent intent = new Intent(getContext(), IniciarSesion.class);
        boolean updated = db.updateData(0, 1,new Datum("Columna1", "primera"));

        startActivity(intent);
    }

    @Override
    public void onResume() {
        actualiza();
        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}