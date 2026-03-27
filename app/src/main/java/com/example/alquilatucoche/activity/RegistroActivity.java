package com.example.alquilatucoche.activity;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity {

    private EditText etEmail, etContrasena, etFechaNacimiento, etLugarNacimiento;
    private Button btConfirmarRegistro, btVolver;
    private Calendar calendarNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registro), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        etFechaNacimiento.setOnClickListener(v -> mostrarDatePicker());
        btConfirmarRegistro.setOnClickListener(v -> registrarUsuario());
        btVolver.setOnClickListener(v -> finish());
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmailRegistro);
        etContrasena = findViewById(R.id.etContrasenaRegistro);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etLugarNacimiento = findViewById(R.id.etLugarNacimiento);
        btConfirmarRegistro = findViewById(R.id.btConfirmarRegistro);
        btVolver = findViewById(R.id.btVolver);
        calendarNacimiento = Calendar.getInstance();
    }

    private void mostrarDatePicker() {
        int year = calendarNacimiento.get(Calendar.YEAR);
        int month = calendarNacimiento.get(Calendar.MONTH);
        int day = calendarNacimiento.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            calendarNacimiento.set(year1, month1, dayOfMonth);
            String fecha = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            etFechaNacimiento.setText(fecha);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void registrarUsuario() {
        String email = etEmail.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String fecha = etFechaNacimiento.getText().toString().trim();
        String lugar = etLugarNacimiento.getText().toString().trim();

        if (validarCampos(email, contrasena, fecha, lugar)) {
            Toast.makeText(this, "Registro completado con éxito", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean validarCampos(String email, String contrasena, String fecha, String lugar) {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email no válido");
            return false;
        }

        if (contrasena.length() < 6) {
            etContrasena.setError("La contraseña debe tener al menos 6 caracteres");
            return false;
        }

        if (fecha.isEmpty()) {
            etFechaNacimiento.setError("Selecciona tu fecha de nacimiento");
            return false;
        }

        if (!esMayorDeEdad()) {
            etFechaNacimiento.setError("Debes ser mayor de 18 años");
            Toast.makeText(this, "Debes ser mayor de 18 años para registrarte", Toast.LENGTH_LONG).show();
            return false;
        }

        if (lugar.isEmpty()) {
            etLugarNacimiento.setError("Introduce tu lugar de nacimiento");
            return false;
        }

        return true;
    }

    private boolean esMayorDeEdad() {
        Calendar hoy = Calendar.getInstance();
        int edad = hoy.get(Calendar.YEAR) - calendarNacimiento.get(Calendar.YEAR);

        if (hoy.get(Calendar.DAY_OF_YEAR) < calendarNacimiento.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        return edad >= 18;
    }
}
