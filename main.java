package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText Imie;
    EditText Nazwisko;
    EditText Email;
    EditText Haslo;
    Button baton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Dopiero po setContentView()
        Imie = findViewById(R.id.imie);
        Nazwisko = findViewById(R.id.nazwisko);
        Email = findViewById(R.id.email);
        Haslo = findViewById(R.id.haslo);
        baton = findViewById(R.id.baton);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        baton.setOnClickListener(v -> {

            if (!checkValues()) {
                return;
            }

            if (!checkEmail()) {
                return;
            }

            if (!checkPassword()) {
                return;
            }

            Toast.makeText(
                    this,
                    "Dane są poprawne!",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    private boolean checkValues() {

        String imie = Imie.getText().toString().trim();
        String nazwisko = Nazwisko.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String haslo = Haslo.getText().toString().trim();

        if (imie.isEmpty()
                || nazwisko.isEmpty()
                || email.isEmpty()
                || haslo.isEmpty()) {

            Toast.makeText(
                    this,
                    "Uzupełnij wszystkie pola",
                    Toast.LENGTH_SHORT
            ).show();

            return false;
        }

        return true;
    }

    private boolean checkEmail() {

        String email = Email.getText().toString().trim();

        if (!email.contains("@") || !email.contains(".")) {

            Toast.makeText(
                    this,
                    "Wpisz poprawny email!",
                    Toast.LENGTH_SHORT
            ).show();

            return false;
        }

        return true;
    }

    private boolean checkPassword() {

        String password = Haslo.getText().toString();

        // Minimum 8 znaków:
        // przynajmniej jedna mała litera,
        // jedna wielka litera,
        // jeden znak specjalny
        if (password.matches(
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$"
        )) {
            return true;
        }

        Toast.makeText(
                this,
                "Hasło musi mieć minimum 8 znaków, małą i wielką literę oraz znak specjalny!",
                Toast.LENGTH_LONG
        ).show();

        return false;
    }
}
