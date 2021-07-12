package com.seriouscorp.gooply.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.seriouscorp.gooply.R;

public class PrincipalActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    FragmentTransaction transaction;
    FloatingActionButton floatingActionButton;
    String clase;
    Class<EditarPerfil> editarPerfilClass = EditarPerfil.class;
    Class<AgregarEventos> agregarEventosClass = AgregarEventos.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        chipNavigationBar = findViewById(R.id.menub);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        //chipNavigationBar.showBadge(R.id.home);

        final Inicio inicio = new Inicio();

        if(savedInstanceState==null){
            chipNavigationBar.setItemSelected(R.id.home, true);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer, inicio).commit();
        }

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home:
                        fragment = new Inicio();
                        floatingActionButton.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.maps:
                        fragment = new MapsFragment();
                        floatingActionButton.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.agenda:
                        fragment = new Agenda();
                        floatingActionButton.setVisibility(View.VISIBLE);
                        floatingActionButton.setImageResource(R.drawable.ic_calendario);
                       clase ="agenda";
                        break;
                    case R.id.perfil:
                        fragment = new Perfil();
                        floatingActionButton.setVisibility(View.VISIBLE);
                        clase = "editarp";
                        floatingActionButton.setImageResource(R.drawable.ic_edit_24);
                        break;
                }
                if(fragment !=null) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentcontainer, fragment).commit();
                    transaction.addToBackStack(null);
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clase.equals("editarp"))
                    startActivity(new Intent( PrincipalActivity.this, editarPerfilClass));
                else
                    if (clase.equals("agenda"))
                        startActivity(new Intent( PrincipalActivity.this, agregarEventosClass));
            }
        });

    }
}