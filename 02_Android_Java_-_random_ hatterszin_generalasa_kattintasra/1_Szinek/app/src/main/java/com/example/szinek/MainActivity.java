package com.example.szinek;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // -- Felhasználható objektumváltozók deklarálása
    private RelativeLayout layout;
    private TextView hatterSzin;
    private Random rnd;

    /*
     // Deklarálás és inicializálás egyszerre, de itt, az onCreate() előtt még nem működne

     private RelativeLayout layout = findViewById(R.id.layout);
     private TextView hatterSzin = findViewById(R.id.hatterSzin);
     private Random rnd = new Random();
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -- Inicializáló metódus meghívása
        init();

        // Háttérre kattintás eseménykezelője
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 0–255 közötti véletlen szám generálása mindhárom alapszínhez
                int red = rnd.nextInt(256);
                int green = rnd.nextInt(256);
                int blue = rnd.nextInt(256);

                // Háttérszín beállítása RGB színből
                layout.setBackgroundColor(Color.rgb(red,green,blue));

                // Szöveg színének beállítása, hogy mindig olvasható maradjon
                if ((red + green + blue) > 450 ){
                    // Világos háttér esetén fekete szöveg
                    hatterSzin.setTextColor(Color.BLACK);
                }
                else{
                    // Sötét háttér esetén fehér szöveg
                    hatterSzin.setTextColor(Color.WHITE);
                }

                // Aktuális RGB érték megjelenítése
                String szin = String.format("(%d, %d, %d)",red,green,blue);
                hatterSzin.setText(szin);

            }
        });
    }

    private void init(){
        // -- Változók inicializálása
        layout = findViewById(R.id.layout);
        hatterSzin = findViewById(R.id.hatterSzin);
        rnd = new Random();
    }
}
