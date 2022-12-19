package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // -- 1. Változók DEKLARÁLÁSA --------------------
    private Button buttonSubmit; // A gomb változója
    private EditText editTextInput;
    private TextView textViewResult;

    // Megjegyzések:
    // - Nem kötelező ugyanazt a nevet használni, mint az XML-ben
    // - A Java nyelv kis- és nagybetűérzékeny

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3. Az inicializáló metódus meghívása - ezzel megtörténik a vezérlőelemek összekötése
        init();

        // Eseménykezelő: ha megnyomják a gombot, kiolvassa a beírt szöveget és beírja a TextView-ba
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // A szöveg lekérése az EditText mezőből (levágja az elejéről/végéről a szóközöket)
                String szoveg = editTextInput.getText().toString().trim();

                // A lekért szöveg beállítása a szövegmegjelenítő mezőbe
                textViewResult.setText(szoveg);
            }
        });
    }

    // -- 2. INICIALIZÁLÁS metódusban ------------------
    // Az XML-ben lévő vezérlőelemek hozzárendelése a Java változókhoz
    private void init(){
        buttonSubmit = findViewById(R.id.buttonSubmit);
        editTextInput = findViewById(R.id.editTextInput);
        textViewResult = findViewById(R.id.textViewResult);
    }
}
