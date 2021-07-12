package com.seriouscorp.gooply.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.seriouscorp.gooply.R;

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.Datum;
import org.richit.easiestsqllib.EasiestDB;

import java.util.Arrays;
import java.util.List;

import static com.seriouscorp.gooply.Presentador.BDSQLeasy.getFase;
import static com.seriouscorp.gooply.Presentador.UtilidadesVarias.irActividad;


public class IniciarSesion extends AppCompatActivity {

    ImageView correo;
    Class activityClass = PrincipalActivity.class;
    Class juego = BoomBall.class;
    ConstraintLayout layout;
    Button b1, b2;
    private FirebaseAuth nfirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public static final int SING_IN = 1;
    List <AuthUI.IdpConfig> provider;
    public static EasiestDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciosesion);

        layout = findViewById(R.id.layoutcargando);
        correo = findViewById(R.id.imageView4);
        b1 = findViewById(R.id.googleSignInButton);
        b2 = findViewById(R.id.button);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        }


        correo.bringToFront();

        db =  EasiestDB.init(getApplicationContext());

        db.addTableColumns("tabla1", new Column("Columna1","nada")).doneAddingTables();
        boolean added = db.addDataInTable(0, new Datum("Columna1","primera"));
        nfirebaseAuth= FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    irActividad(getApplicationContext(), activityClass);
                    boolean updated = db.updateData(0, 1,new Datum("Columna1", "Tercera"));
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(provider)
                                    .setIsSmartLockEnabled(false)
                                    .build(), SING_IN
                    );
                    boolean updated = db.updateData(0, 1,new Datum("Columna1", "primera"));
                }
            }
        };
    }
    //--Al entrar login con google :v
    @Override
    protected void onResume() {
        if(!(getFase(db).equals("primera"))){
            layout.setVisibility(View.VISIBLE);
            layout.bringToFront();
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            nfirebaseAuth.addAuthStateListener(authStateListener);
        }else{
            layout.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(!(getFase(db).equals("primera")))
            nfirebaseAuth.removeAuthStateListener(authStateListener);
        super.onPause();
    }


    public void inicioMail(View view) {
        provider = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());
        boolean updated = db.updateData(0, 1,new Datum("Columna1", "Segunda"));
        nfirebaseAuth.addAuthStateListener(authStateListener);
    }

    public void inicioGoogle(View view) {
        provider = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());
        boolean added = db.addDataInTable(0, new Datum("Columna1","Segunda"));
        nfirebaseAuth.addAuthStateListener(authStateListener);
    }

    public void boomballl(View view) {
        irActividad(getApplicationContext(), juego);
    }
}