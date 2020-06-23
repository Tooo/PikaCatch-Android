package com.cmpt276a3.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cmpt276a3.R;

public class Welcome extends AppCompatActivity {
    private boolean isActivityDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupSkipButton();
        animateImages();
        addDelay();
    }

    private void setupSkipButton() {
        Button button = findViewById(R.id.welc_btnSkip);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                isActivityDone = true;
                exitWelcome();
            }
        });
    }

    private void animateImages() {
        ImageView imagePikachu = findViewById(R.id.welc_imgPikachu);
        ImageView imagePokeball = findViewById(R.id.welc_imgPokeball);
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator pikachuMove = ObjectAnimator.ofFloat(imagePikachu, "translationX", -750);
        pikachuMove.setDuration(2000);

        ObjectAnimator pikachuHide = ObjectAnimator.ofFloat(imagePikachu, "alpha", 0);
        pikachuHide.setDuration(500);

        ObjectAnimator pikachuShake = ObjectAnimator.ofFloat(imagePikachu, "rotation", 360);
        pikachuHide.setDuration(500);

        ObjectAnimator pokeballShake = ObjectAnimator.ofFloat(imagePokeball, "rotationX", 1800);
        pokeballShake.setDuration(500);

        animatorSet.play(pikachuMove).before(pikachuHide);
        animatorSet.play(pikachuHide).with(pikachuShake);
        animatorSet.play(pokeballShake).after(pikachuHide);

        animatorSet.start();
    }

    // Refer to StackOverflow
    // https://stackoverflow.com/questions/37323110/how-to-use-delay-functions-in-android-studio/37323343#comment62167379_37323110
    private void addDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isActivityDone) {
                    exitWelcome();
                }
            }
        }, 7000);

    }

    private void exitWelcome() {
        Intent intent = new Intent(Welcome.this, MainMenu.class);
        finish();
        startActivity(intent);
    }
}