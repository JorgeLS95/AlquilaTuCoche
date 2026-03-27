package com.example.alquilatucoche.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquilatucoche.R;
import com.example.alquilatucoche.adapter.CocheAdapter;
import com.example.alquilatucoche.model.Coche;

import java.util.ArrayList;
import java.util.List;

public class ListaCochesFragment extends Fragment {

    private RecyclerView rvCoches;
    private CocheAdapter adapter;
    private List<Coche> listaCoches;

    public ListaCochesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_coches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        rvCoches = view.findViewById(R.id.rvCoches);
        rvCoches.setLayoutManager(new LinearLayoutManager(getContext()));

        // Datos de prueba (Simulando que ya hay coches introducidos)
        cargarDatosPrueba();

        adapter = new CocheAdapter(listaCoches);
        rvCoches.setAdapter(adapter);
    }

    private void cargarDatosPrueba() {
        listaCoches = new ArrayList<>();
        listaCoches.add(new Coche("Seat Arona - Rojo - 2023", R.drawable.coche));
        listaCoches.add(new Coche("Seat Ibiza - Blanco - 2022", R.drawable.coche));
        listaCoches.add(new Coche("Volkswagen Golf - Negro - 2021", R.drawable.coche));
        listaCoches.add(new Coche("Toyota Corolla - Gris - 2024", R.drawable.coche));
    }
}
