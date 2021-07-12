package com.seriouscorp.gooply.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.jawnnypoo.physicslayout.Physics;
import com.jawnnypoo.physicslayout.PhysicsFrameLayout;
import com.seriouscorp.gooply.R;

import org.jbox2d.common.Vec2;

public class BoomBall2 extends AppCompatActivity implements SensorEventListener {

    PhysicsFrameLayout physicsFrameLayout;
    LottieAnimationView ball, buu;
    int pu = 1;
    @SuppressLint("SourceLockedOrientationActivity")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boom_ball2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        physicsFrameLayout = findViewById(R.id.physicsLayout5);
        ball = findViewById(R.id.bolita2);
        buu = findViewById(R.id.buuu);

        SensorManager sadmin = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor rota = sadmin.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sadmin.registerListener(this, rota, SensorManager.SENSOR_DELAY_FASTEST);

        final Vec2 vector = new Vec2(6, -6);
        Vec2 vector2 = new Vec2(6, -6);

        physicsFrameLayout.getPhysics().createWorld();

        final Vec2 p = physicsFrameLayout.getPhysics().findBodyById(R.id.bolita2).getLocalCenter();

        physicsFrameLayout.getPhysics().findBodyById(R.id.buuu).applyLinearImpulse(vector, p);
        physicsFrameLayout.getPhysics().findBodyById(R.id.bolita2).applyLinearImpulse(vector2, p);


        physicsFrameLayout.getPhysics().setOnCollisionListener(new Physics.OnCollisionListener() {
            @Override
            public void onCollisionEntered(int viewIdA, int viewIdB) {
                if(pu % 500 == 0){
                    physicsFrameLayout.getPhysics().giveRandomImpulse();
                }
                if(viewIdA == R.id.bolita2 && viewIdB == R.id.buuu){
                    FlatDialog flatDialog = new FlatDialog(BoomBall2.this);
                    flatDialog.setBackgroundColor(R.color.rojo);
                    flatDialog.setTitle("Perdiste :c");
                    flatDialog.setSubtitle(String.format("Puntuación: "+ pu));
                    flatDialog.setFirstButtonText("Jugar otra vez").withFirstButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),BoomBall2.class);
                            startActivity(intent);
                        }
                    });
                    flatDialog.setSecondButtonText("Salir").withSecondButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),IniciarSesion.class);
                            startActivity(intent);
                        }
                    });
                    flatDialog.show();
                    pu = 0;
                    physicsFrameLayout.getPhysics().disablePhysics();
                }
            }

            @Override
            public void onCollisionExited(int viewIdA, int viewIdB) {

            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        pu++;
        final float alpha = 0.8f;
        float[] gravity = new float[3];
        float[] linear_acceleration = new float[3];

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        physicsFrameLayout.getPhysics().setGravityY(linear_acceleration[1]);
        physicsFrameLayout.getPhysics().setGravityX(-linear_acceleration[0]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}