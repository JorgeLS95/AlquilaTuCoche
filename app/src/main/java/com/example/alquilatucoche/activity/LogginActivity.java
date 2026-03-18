package com.example.alquilatucoche.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alquilatucoche.R;

public class LogginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etContrasena;
    private Button btAcceder;
    private Button btRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loggin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loggin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.etEmail);
        etContrasena = findViewById(R.id.etContrasena);
        btAcceder = findViewById(R.id.btAcceder);
        btRegistrar = findViewById(R.id.btRegistrar);

        btAcceder.setOnClickListener(v -> loginUser());
        btRegistrar.setOnClickListener(v -> abrirRegistro());
    }

    private void abrirRegistro() {
        Intent intent = new Intent(LogginActivity.this,RegistroActivity.class);
        startActivity(intent);
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();

        if (!validarCampos(email, contrasena)) {
            return;
        }

        // LOGIN PROVISIONAL
        if (email.equals("test@test.comtest") && contrasena.equals("1234")) {
            Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LogginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCampos(String email, String password) {

        if (email.isEmpty()) {
            etEmail.setError("Introduce tu correo");
            etEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Formato de correo no válido");
            etEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            etContrasena.setError("Introduce la contraseña");
            etContrasena.requestFocus();
            return false;
        }

        if (password.length() < 4) {
            etContrasena.setError("La contraseña debe tener al menos 4 caracteres");
            etContrasena.requestFocus();
            return false;
        }

        return true;
    }

}