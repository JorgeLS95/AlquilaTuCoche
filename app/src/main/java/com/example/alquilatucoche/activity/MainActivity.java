package com.example.alquilatucoche.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.alquilatucoche.R;
import com.example.alquilatucoche.fragment.ListaCochesFragment;
import com.example.alquilatucoche.fragment.MapaCochesFragment;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnLista, btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        // Cargar fragmento inicial (Lista)
        if (savedInstanceState == null) {
            reemplazarFragmento(new ListaCochesFragment());
        }

        btnLista.setOnClickListener(v -> reemplazarFragmento(new ListaCochesFragment()));
        btnMapa.setOnClickListener(v -> reemplazarFragmento(new MapaCochesFragment()));
    }

    private void initViews() {
        btnLista = findViewById(R.id.btnLista);
        btnMapa = findViewById(R.id.btnMapa);
    }

    private void reemplazarFragmento(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
