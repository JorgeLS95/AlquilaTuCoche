package com.example.alquilatucoche.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alquilatucoche.R;
import com.example.alquilatucoche.model.Coche;

import java.util.Calendar;

public class DetalleCocheActivity extends AppCompatActivity {

    private ImageView ivCoche;
    private TextView tvNombre, tvPrecio, tvDescripcion;
    private EditText etFechaInicio, etFechaFin;
    private Button btReservar;
    private ImageButton btnVolver;
    private Coche coche;
    private Calendar calendarInicio, calendarFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_coche);

        coche = (Coche) getIntent().getSerializableExtra("COCHE_DATA");

        initViews();
        if (coche != null) {
            populateData();
        }

        etFechaInicio.setOnClickListener(v -> mostrarDatePicker(true));
        etFechaFin.setOnClickListener(v -> mostrarDatePicker(false));
        btReservar.setOnClickListener(v -> realizarReserva());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void initViews() {
        ivCoche = findViewById(R.id.ivDetalleCoche);
        tvNombre = findViewById(R.id.tvDetalleNombre);
        tvPrecio = findViewById(R.id.tvDetallePrecio);
        tvDescripcion = findViewById(R.id.tvDetalleDescripcion);
        etFechaInicio = findViewById(R.id.etFechaInicio);
        etFechaFin = findViewById(R.id.etFechaFin);
        btReservar = findViewById(R.id.btConfirmarReserva);
        btnVolver = findViewById(R.id.btnVolverDetalle);

        calendarInicio = Calendar.getInstance();
        calendarFin = Calendar.getInstance();
    }

    private void populateData() {
        tvNombre.setText(coche.getNombreCompleto());
        tvPrecio.setText(coche.getPrecioDia() + "€ / día");
        tvDescripcion.setText(coche.getDescripcion());
        ivCoche.setImageResource(coche.getImagenResId());
    }

    private void mostrarDatePicker(boolean esInicio) {
        Calendar calendarRef = esInicio ? calendarInicio : calendarFin;
        int year = calendarRef.get(Calendar.YEAR);
        int month = calendarRef.get(Calendar.MONTH);
        int day = calendarRef.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String fecha = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            if (esInicio) {
                calendarInicio.set(year1, month1, dayOfMonth);
                etFechaInicio.setText(fecha);
            } else {
                calendarFin.set(year1, month1, dayOfMonth);
                etFechaFin.setText(fecha);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void realizarReserva() {
        String fInicio = etFechaInicio.getText().toString();
        String fFin = etFechaFin.getText().toString();

        if (fInicio.isEmpty() || fFin.isEmpty()) {
            Toast.makeText(this, "Por favor, selecciona las fechas", Toast.LENGTH_SHORT).show();
            return;
        }

        if (calendarFin.before(calendarInicio)) {
            Toast.makeText(this, "La fecha de fin no puede ser anterior a la de inicio", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lógica de reserva (simulada)
        Toast.makeText(this, "Reserva realizada con éxito para " + coche.getNombreCompleto(), Toast.LENGTH_LONG).show();
        finish();
    }
}
