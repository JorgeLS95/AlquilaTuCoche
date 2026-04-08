package com.example.alquilatucoche.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquilatucoche.R;
import com.example.alquilatucoche.activity.LogginActivity;
import com.example.alquilatucoche.adapter.CocheAdapter;
import com.example.alquilatucoche.adapter.ReservaAdapter;
import com.example.alquilatucoche.model.Coche;
import com.example.alquilatucoche.model.Reserva;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    private static final String ARG_EMAIL = "user_email";

    private Button btCerrarSesion, btEditarPerfil;
    private TextView tvNombre, tvEmail, tvRol, tvPasswordCodificada;
    private LinearLayout layoutHeaderReservas, layoutHeaderMisCoches;
    private RecyclerView rvReservas, rvMisCoches;
    private ImageView ivFlechaReservas, ivFlechaMisCoches;

    private String userEmail;
    private String userName = "Jorge Propietario";
    private String userPassword = "password123"; // Simulación
    private boolean esPropietario = true;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(String email) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmail = getArguments().getString(ARG_EMAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupUserData();
        setupReservas();
        setupMisCoches();

        layoutHeaderReservas.setOnClickListener(v -> toggleVisibility(rvReservas, ivFlechaReservas));
        layoutHeaderMisCoches.setOnClickListener(v -> toggleVisibility(rvMisCoches, ivFlechaMisCoches));

        btEditarPerfil.setOnClickListener(v -> mostrarDialogoEdicion());

        btCerrarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LogginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }

    private void mostrarDialogoEdicion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_editar_perfil, null);
        builder.setView(dialogView);

        EditText etNombre = dialogView.findViewById(R.id.etEditarNombre);
        EditText etEmail = dialogView.findViewById(R.id.etEditarEmail);
        EditText etPassword = dialogView.findViewById(R.id.etEditarPassword);
        MaterialSwitch swRol = dialogView.findViewById(R.id.swEditarRol);
        Button btnGuardar = dialogView.findViewById(R.id.btnGuardarEdit);
        Button btnCancelar = dialogView.findViewById(R.id.btnCancelarEdit);

        // Pre-cargar datos actuales
        etNombre.setText(userName);
        etEmail.setText(userEmail);
        swRol.setChecked(esPropietario);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        btnGuardar.setOnClickListener(v -> {
            String nuevoNombre = etNombre.getText().toString().trim();
            String nuevoEmail = etEmail.getText().toString().trim();
            String nuevaPass = etPassword.getText().toString().trim();
            boolean nuevoRol = swRol.isChecked();

            if (nuevoNombre.isEmpty() || nuevoEmail.isEmpty()) {
                Toast.makeText(getContext(), "El nombre y el email no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Actualizar datos locales y UI
            userName = nuevoNombre;
            userEmail = nuevoEmail;
            esPropietario = nuevoRol;
            
            if (!nuevaPass.isEmpty()) {
                userPassword = nuevaPass;
                Toast.makeText(getContext(), "Contraseña actualizada", Toast.LENGTH_SHORT).show();
            }

            setupUserData();
            Toast.makeText(getContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        btnCancelar.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void initViews(View view) {
        tvNombre = view.findViewById(R.id.tvNombreUsuario);
        tvEmail = view.findViewById(R.id.tvEmailUsuario);
        tvPasswordCodificada = view.findViewById(R.id.tvPasswordCodificada);
        tvRol = view.findViewById(R.id.tvRolUsuario);
        btEditarPerfil = view.findViewById(R.id.btEditarPerfil);
        btCerrarSesion = view.findViewById(R.id.btCerrarSesion);

        layoutHeaderReservas = view.findViewById(R.id.layoutHeaderReservas);
        rvReservas = view.findViewById(R.id.rvReservas);
        ivFlechaReservas = view.findViewById(R.id.ivFlechaReservas);

        layoutHeaderMisCoches = view.findViewById(R.id.layoutHeaderMisCoches);
        rvMisCoches = view.findViewById(R.id.rvMisCoches);
        ivFlechaMisCoches = view.findViewById(R.id.ivFlechaMisCoches);
    }

    private void setupUserData() {
        tvNombre.setText(userName);
        tvEmail.setText(userEmail != null ? userEmail : "jorge@ejemplo.com");
        
        // Mostrar contraseña codificada (asteriscos según longitud)
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < userPassword.length(); i++) stars.append("*");
        tvPasswordCodificada.setText(stars.toString());
        
        if (esPropietario) {
            tvRol.setText("Rol: Propietario");
            layoutHeaderMisCoches.setVisibility(View.VISIBLE);
        } else {
            tvRol.setText("Rol: Usuario");
            layoutHeaderMisCoches.setVisibility(View.GONE);
            rvMisCoches.setVisibility(View.GONE);
        }
    }

    private void setupReservas() {
        List<Reserva> listaReservas = new ArrayList<>();
        listaReservas.add(new Reserva("Seat Arona - Rojo", "10/05/2024", 45.0));
        listaReservas.add(new Reserva("Volkswagen Golf - Negro", "15/05/2024", 60.0));

        ReservaAdapter adapter = new ReservaAdapter(listaReservas);
        rvReservas.setLayoutManager(new LinearLayoutManager(getContext()));
        rvReservas.setAdapter(adapter);
    }

    private void setupMisCoches() {
        List<Coche> listaCoches = new ArrayList<>();
        listaCoches.add(new Coche("Seat", "Arona", "Rojo - 2023. Perfecto estado.", 45.0, R.drawable.coche));
        listaCoches.add(new Coche("Ford", "Focus", "Azul - 2021. Muy cuidado.", 40.0, R.drawable.coche));

        CocheAdapter adapter = new CocheAdapter(listaCoches);
        rvMisCoches.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMisCoches.setAdapter(adapter);
    }

    private void toggleVisibility(View view, ImageView arrow) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
            arrow.setImageResource(android.R.drawable.arrow_up_float);
        } else {
            view.setVisibility(View.GONE);
            arrow.setImageResource(android.R.drawable.arrow_down_float);
        }
    }
}
