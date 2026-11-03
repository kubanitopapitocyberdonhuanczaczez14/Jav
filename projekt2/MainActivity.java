
package com.example.projekt_2;

import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextLiczbaKostek;
    Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    GridLayout gridKostki;
    TextView textViewWyniki;

    ArrayList<Integer> wyniki = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);

        buttonLosuj.setOnClickListener(v -> losuj());
        buttonRosnaco.setOnClickListener(v -> sortuj(true));
        buttonMalejaco.setOnClickListener(v -> sortuj(false));
    }

    private void losuj() {

        String tekst = editTextLiczbaKostek.getText().toString();

        if (tekst.isEmpty()) {
            Toast.makeText(this, "Podaj liczbe kostek", Toast.LENGTH_SHORT).show();
            return;
        }

        int liczba;

        try {
            liczba = Integer.parseInt(tekst);
        } catch (Exception e) {
            Toast.makeText(this, "Podaj prawidlowa liczbe", Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba <= 0 || liczba > 100) {
            Toast.makeText(this, "Podaj liczbe od 1 do 100", Toast.LENGTH_SHORT).show();
            return;
        }

        wyniki.clear();

        for (int i = 0; i < liczba; i++) {
            wyniki.add(random.nextInt(6) + 1);
        }

        wyswietl();
    }

    private void sortuj(boolean rosnaco) {

        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < wyniki.size(); i++) {
            for (int j = 0; j < wyniki.size() - 1; j++) {

                if (rosnaco && wyniki.get(j) > wyniki.get(j + 1) ||
                        !rosnaco && wyniki.get(j) < wyniki.get(j + 1)) {

                    int pomoc = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, pomoc);
                }
            }
        }

        wyswietl();
    }

    private void wyswietl() {

        String tekst = "";

        gridKostki.removeAllViews();
        gridKostki.setColumnCount(5);

        for (int wynik : wyniki) {

            tekst += wynik + " ";

            TextView kostka = new TextView(this);

            kostka.setText(String.valueOf(wynik));
            kostka.setTextSize(24);
            kostka.setTextColor(Color.BLACK);
            kostka.setGravity(Gravity.CENTER);
            kostka.setBackgroundColor(Color.LTGRAY);

            GridLayout.LayoutParams parametry = new GridLayout.LayoutParams();
            parametry.width = 100;
            parametry.height = 100;
            parametry.setMargins(8, 8, 8, 8);

            kostka.setLayoutParams(parametry);

            gridKostki.addView(kostka);
        }

        textViewWyniki.setText(tekst);
    }
}
