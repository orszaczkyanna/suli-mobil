package com.example.idoszamlalo;

import static java.util.Calendar.getInstance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //import java.util.Date;
    //import java.util.Timer;

    // Komponensek és segédváltozók deklarálása
    private TextView vizsgaSzoveg;
    private Timer timer;
    private Date vizsgaIdopont;

    private long masodpercMilli, percMilli, oraMilli, napMilli;

    // Felület beállítása, változók inicializálása
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // Szál és időzített feladat elindítása, amikor az Activity láthatóvá válik (pl. indításkor vagy visszatéréskor)
    @Override
    protected void onStart() {
        // A szülőosztály onStart() metódusa az alapbeállításokhoz szükséges
        super.onStart();

        // Új időzítő objektum (Timer) létrehozása, ami háttérben képes feladatokat futtatni
        timer = new Timer();

        // Olyan feladat (TimerTask) létrehozása, ami 500 ms-onként újraszámolja a vizsgáig hátralévő időt
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Date most = getInstance().getTime(); // aktuális idő
                long hatralevoIdo = vizsgaIdopont.getTime() - most.getTime();

                long nap = hatralevoIdo/napMilli;
                hatralevoIdo %= napMilli; // % egészrészes osztás

                long ora = hatralevoIdo/oraMilli;
                hatralevoIdo %= oraMilli;

                long perc = hatralevoIdo/percMilli;
                hatralevoIdo %= percMilli;

                long masodperc = hatralevoIdo/masodpercMilli ;

                // Formázott visszaszámláló szöveg: "X nap HH:MM:SS"
                // %02 → 2 számjegy, balról nullával feltöltve
                String hatralevoSzoveg = String.format("%d nap %02d:%02d:%02d",nap,ora,perc,masodperc);

                // runOnUiThread(): a kódot a fő UI szálon futtatja, hogy frissíthesse a felületet (itt visszaszámláló "X nap HH:MM:SS" szöveget)
                runOnUiThread(() -> {
                    // () -> { ... }
                    // lambda kifejezés, azaz rövidített névtelen függvény
                    vizsgaSzoveg.setText(hatralevoSzoveg);
                });
            }
        };

        // timerTask elindítása (a timer schedule metódusával)
        timer.schedule(timerTask,0,500);
        // - timerTask: a szál, amit el akarunk indítani
        // - delay: hány ezredmásodpercet várjon az első futás előtt (0 = azonnal indul)
        // - period: milyen időközönként fusson újra (ez is ms-ban)

    }

    // Szál leállítása, ha az aktivitás már nem látható
    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    // Inicializálja a komponenseket és kiszámítja az időegységek milliszekundumban kifejezett értékét
    private void init(){
        vizsgaSzoveg = (TextView) findViewById(R.id.vizsgaSzoveg);
        // (TextView) → típus-kényszerítés

        // -- Komplex vizsga időpontja: 2023.05.31 9:00 ---

        // Naptárobjektum létrehozása (alapértelmezett: az aktuális dátum/idő szerint)
        Calendar vizsga = Calendar.getInstance();

        // Vizsga időpontjának beállítása
        // A hónap nullától indexelt (4 = május)
        vizsga.set(2023,4,31,9,0,0);

        // A Calendar típusú időpont átalakítása Date objektummá (későbbi számításokhoz)
        this.vizsgaIdopont = vizsga.getTime();

        // Időegységek milliszekundumban
        masodpercMilli = 1000;
        percMilli = masodpercMilli * 60;
        oraMilli = percMilli * 60;
        napMilli = oraMilli * 24;
    }
}
