package com.seriouscorp.gooply.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.jawnnypoo.physicslayout.PhysicsFrameLayout;
import com.seriouscorp.gooply.R;

import org.jbox2d.common.Vec2;

public class BoomBall extends AppCompatActivity {

    PhysicsFrameLayout playout;
    TextView letrasTitulo;
    LottieAnimationView ball, play;
    Boolean bb = false;

    @SuppressLint("SourceLockedOrientationActivity")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boom_ball);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        playout = findViewById(R.id.physicsLayout);
        letrasTitulo = findViewById(R.id.letrasTitulo);
        ball = findViewById(R.id.bolita);
        play = findViewById(R.id.lottieAnimationView2);

        playout.getPhysics().enableFling();
        playout.getPhysics().createWorld();

        Vec2 vector = new Vec2(1, -3);
        Vec2 p = playout.getPhysics().findBodyById(R.id.bolita).getLocalCenter();

        playout.getPhysics().findBodyById(R.id.bolita).applyLinearImpulse(vector, p);

        ball.bringToFront();
        letrasTitulo.bringToFront();

        final Intent intent = new Intent(getApplicationContext(),BoomBall2.class);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bb = true;
            }
        });

        play.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                if (bb) {
                    bb = false;
                    startActivity(intent);
                }
            }
        });
    }
}