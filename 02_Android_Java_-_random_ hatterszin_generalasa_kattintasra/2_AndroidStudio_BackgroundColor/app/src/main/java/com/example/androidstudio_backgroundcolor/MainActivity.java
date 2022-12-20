package com.example.androidstudio_backgroundcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Felhasználható objektumok
    private RelativeLayout layout;
    private TextView rgbSzoveg;
    private Random r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // Esemény a képernyőre kattintáskor
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Random szín generálása háttérszínnek
                int red = r.nextInt(256);
                int green = r.nextInt(256);
                int blue = r.nextInt(256);

                layout.setBackgroundColor(Color.rgb(red,green,blue));


                // Generált szín RGB kódjának kiíratása
                String rgbStringkent = String.format("(%d, %d, %d)",red,green,blue);
                rgbSzoveg.setText(rgbStringkent);


                // Szöveg színének beállítása, hogy olvasható legyen
                if ( (red + green + blue) > 450 ){
                    rgbSzoveg.setTextColor(Color.BLACK);
                }
                else{
                    rgbSzoveg.setTextColor(Color.WHITE);
                }
            }
        });
    }

    // Inicializálás
    private void init(){
        layout = findViewById(R.id.layout);
        rgbSzoveg = findViewById(R.id.rgbSzoveg);
        r = new Random();
    }
}
