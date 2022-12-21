package com.example.megosztas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonKuldes;


    // Beállítja a felületet, inicializálja a változókat, és eseménykezelőt rendel a gombhoz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // Kattintáskezelő a 'Küldés' gombra
        buttonKuldes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatmentes();
            }
        });

    }

     // Beolvassa a felhasználó által beírt szöveget,
     // majd eltárolja azt SharedPreferences segítségével,
     // és elindítja a második képernyőt
    private void adatmentes() {
        // Beírt szöveg kiolvasása
        String seged = editTextInput.getText().toString().trim();
        if (seged.equals(null)){
            editTextInput.setError("Nem adott meg adatot");
        } else {
            // -- Szöveg mentése SharedPreferences-be (belső, privát fájlba) --------------------
            SharedPreferences sp = getSharedPreferences("Data", Context.MODE_PRIVATE);
            // - "Data" néven jön létre egy XML adatfájl az alkalmazás belső tárhelyén
            // - Context.MODE_PRIVATE: az adatfájl csak az aktuális alkalmazás számára érhető el

            // Szerkesztő példány (editor) létrehozása, amely segítségével a Data.XML-be lehet írni
            SharedPreferences.Editor editor = sp.edit();

            // Beírt szöveg mentése "editTextErteke" kulcshoz
            editor.putString("editTextErteke", seged);

            // Fájl lezárása, írás befejezése
            editor.apply();

            // Eredményül ez jönne létre az XML fájlban:
            // <string name="editTextErteke">Felhasználó által beírt szöveg</string>


            /*
             // Alternatív példa: mentés más kulccsal

             String valtozo = "Változó tartalma";
             editor.putString("kulcsNev", valtozo);
             editor.apply();

             // Ez kerül a SharedPreferences XML-fájlba:
             // <string name="kulcsNev">Változó tartalma</string>
            */

            // -- Második aktivitás/képernyő  elindítása ----------------------------------------
            // Intent( Honnan, Hova ): az aktuális képernyőről a másodikra lép
            // Intent( Hívó fél, Kit hív )
            Intent intent = new Intent(MainActivity.this, MasodikActivity.class);
            startActivity(intent);

            // Aktuális aktivitás lezárása: kilépés a képernyőről
            // → eltávolítja az aktivitást a stack-ből, így a felhasználó nem tud "visszalépni" rá
            finish();
        }
    }

    private void init(){
        editTextInput = findViewById(R.id.editTextInput);
        buttonKuldes = findViewById(R.id.buttonKuldes);
    }

}