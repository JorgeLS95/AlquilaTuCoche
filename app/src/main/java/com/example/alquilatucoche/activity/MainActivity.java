package com.example.alquilatucoche.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

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
import com.example.alquilatucoche.fragment.PerfilFragment;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnLista, btnMapa, btnPerfil;
    private TextView tvTitulo;
    private String userEmail;

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

        // Recibir el email del Intent
        userEmail = getIntent().getStringExtra("USER_EMAIL");
        if (userEmail == null) userEmail = "invitado@ejemplo.com";

        initViews();

        if (savedInstanceState == null) {
            reemplazarFragmento(new ListaCochesFragment(), "Coches Disponibles");
        }

        btnLista.setOnClickListener(v -> reemplazarFragmento(new ListaCochesFragment(), "Coches Disponibles"));
        btnMapa.setOnClickListener(v -> reemplazarFragmento(new MapaCochesFragment(), "Mapa de Coches"));
        btnPerfil.setOnClickListener(v -> {
            // Pasar el email al fragmento de perfil
            PerfilFragment perfilFragment = PerfilFragment.newInstance(userEmail);
            reemplazarFragmento(perfilFragment, "Mi Perfil");
        });
    }

    private void initViews() {
        btnLista = findViewById(R.id.btnLista);
        btnMapa = findViewById(R.id.btnMapa);
        btnPerfil = findViewById(R.id.btnPerfil);
        tvTitulo = findViewById(R.id.tvMainTitulo);
    }

    private void reemplazarFragmento(Fragment fragment, String titulo) {
        tvTitulo.setText(titulo);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
