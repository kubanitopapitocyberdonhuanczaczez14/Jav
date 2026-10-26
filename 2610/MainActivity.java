package com.example.a2610;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
        import java.io.*;
        import java.util.ArrayList;
public class MainActivity extends Activity {
    EditText nazwa, tekst;
    ListView lista;
    ArrayList<String> pliki = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nazwa = findViewById(R.id.nazwa);
        tekst = findViewById(R.id.tekst);
        lista = findViewById(R.id.lista);
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pliki
        );
        lista.setAdapter(adapter);
        Button nowy = findViewById(R.id.nowy);
        Button zapisz = findViewById(R.id.zapisz);
        Button otworz = findViewById(R.id.otworz);
        Button zamknij = findViewById(R.id.zamknij);
        Button usun = findViewById(R.id.usun);
        nowy.setOnClickListener(v -> {
            nazwa.setText("");
            tekst.setText("");
        });
        zapisz.setOnClickListener(v -> {
            String nazwaPliku = nazwa.getText().toString().trim();
            if (nazwaPliku.isEmpty()) return;
            try {
                FileOutputStream fos = openFileOutput(
                        nazwaPliku, MODE_PRIVATE);
                fos.write(tekst.getText().toString().getBytes());
                fos.close();
                if (!pliki.contains(nazwaPliku)) {
                    pliki.add(nazwaPliku);
                    adapter.notifyDataSetChanged();
                }
                Toast.makeText(this, "Zapisano",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        otworz.setOnClickListener(v -> {
            String nazwaPliku = nazwa.getText().toString().trim();
            try {
                FileInputStream fis = openFileInput(nazwaPliku);
                byte[] dane = new byte[fis.available()];
                fis.read(dane);
                fis.close();
                tekst.setText(new String(dane));
            } catch (Exception e) {
                Toast.makeText(this, "Nie znaleziono pliku",
                        Toast.LENGTH_SHORT).show();
            }
        });
        lista.setOnItemClickListener((parent, view, position, id) -> {
            String plik = pliki.get(position);
            nazwa.setText(plik);
            try {
                FileInputStream fis = openFileInput(plik);
                byte[] dane = new byte[fis.available()];
                fis.read(dane);
                fis.close();
                tekst.setText(new String(dane));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        usun.setOnClickListener(v -> {
            String plik = nazwa.getText().toString().trim();
            if (deleteFile(plik)) {
                pliki.remove(plik);
                adapter.notifyDataSetChanged();
                nazwa.setText("");
                tekst.setText("");
            }
        });
        zamknij.setOnClickListener(v -> finish());
    }
}