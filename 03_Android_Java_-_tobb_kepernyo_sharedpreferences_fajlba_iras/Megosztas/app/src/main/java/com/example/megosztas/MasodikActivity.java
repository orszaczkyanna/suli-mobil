package com.example.megosztas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MasodikActivity extends AppCompatActivity {

    private TextView textViewResult;
    private Button buttonVissza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masodik);
        init();

        // MainActivity-ben megadott szöveg beolvasása és megjelenítése
        String seged = Beolvasas();
        textViewResult.setText(seged);

        // -- MainActivity (első képernyő) elindítása ----------------------------------------
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MasodikActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private String Beolvasas() {
        SharedPreferences sp = getSharedPreferences("Data", Context.MODE_PRIVATE);

        // A mentett szöveg visszaolvasása
        // Ha nem tudja kiolvasni az adatot, nem létezik a fájl, a "hiba" szöveg jelenik meg
        String result = sp.getString("editTextErteke", "hiba");

        return result;
    }

    private void init(){
        textViewResult = findViewById(R.id.textViewResult);
        buttonVissza = findViewById(R.id.buttonVissza);
    }

}