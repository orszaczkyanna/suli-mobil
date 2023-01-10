package com.example.animacioapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonBounce, buttonFadeIn, buttonFadeOut, buttonRotate, buttonZoomIn, buttonZoomOut, buttonFrameAnim;
    private TextView textViewBounce, textViewFadeIn, textViewFadeOut, textViewRotate, textViewZoomIn, textViewZoomOut;
    private ImageView imageViewFrameAnim;

    private Animation bounce, fadeIn, fadeOut, rotate, zoomIn, zoomOut;

    private boolean running; // Frame animáció fut-e jelenleg?



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        // Eseménykezelők beállítása:
        // gombnyomásra elindítja a hozzá tartozó animációt a megfelelő szövegen
        buttonBounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewBounce.startAnimation(bounce);
            }
        });
        buttonFadeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewFadeIn.startAnimation(fadeIn);
            }
        });
        buttonFadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewFadeOut.startAnimation(fadeOut);
            }
        });
        buttonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewRotate.startAnimation(rotate);
            }
        });
        buttonZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewZoomIn.startAnimation(zoomIn);
            }
        });
        buttonZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewZoomOut.startAnimation(zoomOut);
            }
        });

        // Frame animáció indítása/leállítása gombnyomásra
        buttonFrameAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running){
                    ((AnimationDrawable) imageViewFrameAnim.getBackground()).stop();
                    running = false;
                }
                else{
                    ((AnimationDrawable)imageViewFrameAnim.getBackground()).start();
                    running = true;
                }
            }
        });
    }

    // Felületi elemek és animációk inicializálása
    private void init(){
        // Gombok
        buttonBounce = findViewById(R.id.buttonBounce);
        buttonFadeIn = findViewById(R.id.buttonFadeIn);
        buttonFadeOut = findViewById(R.id.buttonFadeOut);
        buttonRotate = findViewById(R.id.buttonRotate);
        buttonZoomIn = findViewById(R.id.buttonZoomIn);
        buttonZoomOut = findViewById(R.id.buttonZoomOut);
        buttonFrameAnim = findViewById(R.id.buttonFrameAnim);

        // Szövegmezők
        textViewBounce = findViewById(R.id.textViewBounce);
        textViewFadeIn = findViewById(R.id.textViewFadeIn);
        textViewFadeOut = findViewById(R.id.textViewFadeOut);
        textViewRotate = findViewById(R.id.textViewRotate);
        textViewZoomIn = findViewById(R.id.textViewZoomIn);
        textViewZoomOut = findViewById(R.id.textViewZoomOut);

        // Animációk betöltése res/anim mappából
        bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
        rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        zoomIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
        zoomOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);

        // Frame animáció komponens
        imageViewFrameAnim = findViewById(R.id.imageViewFrameAnim);

        running = false; // alapértelmezetten nem fut a frame animáció
    }
}
