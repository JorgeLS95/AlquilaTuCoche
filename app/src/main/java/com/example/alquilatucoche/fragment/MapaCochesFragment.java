package com.example.alquilatucoche.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.alquilatucoche.R;
import com.example.alquilatucoche.activity.DetalleCocheActivity;
import com.example.alquilatucoche.model.Coche;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.Random;

public class MapaCochesFragment extends Fragment {

    private MapView mapView;

    public MapaCochesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        // Configuración necesaria para osmdroid
        Context ctx = requireActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        View view = inflater.inflate(R.layout.fragment_mapa_coches, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        
        // Habilitar controles de zoom
        mapView.setMultiTouchControls(true);

        // Coordenadas centrales de Murcia
        double murciaLat = 37.9870400;
        double murciaLon = -1.1300400;
        GeoPoint murciaPoint = new GeoPoint(murciaLat, murciaLon);
        
        mapView.getController().setZoom(14.0);
        mapView.getController().setCenter(murciaPoint);

        // Añadir coches aleatorios por Murcia
        añadirCochesAleatorios(murciaLat, murciaLon);

        return view;
    }

    private void añadirCochesAleatorios(double lat, double lon) {
        String[] marcas = {"Seat", "Volkswagen", "Toyota", "Ford", "Seat"};
        String[] modelos = {"Arona", "Golf", "Corolla", "Focus", "Ibiza"};
        Random random = new Random();
        
        Drawable carIcon = ContextCompat.getDrawable(getContext(), R.drawable.coche);

        for (int i = 0; i < 8; i++) {
            double randomLat = lat + (random.nextDouble() - 0.5) / 50;
            double randomLon = lon + (random.nextDouble() - 0.5) / 50;
            
            GeoPoint carPoint = new GeoPoint(randomLat, randomLon);
            int index = random.nextInt(marcas.length);
            String marca = marcas[index];
            String modelo = modelos[index];
            double precio = 30 + random.nextInt(40);

            Coche coche = new Coche(marca, modelo, "Coche en perfecto estado, ideal para ciudad y viajes cortos.", precio, R.drawable.coche);

            Marker marker = new Marker(mapView);
            marker.setPosition(carPoint);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setTitle(coche.getNombreCompleto());
            marker.setSnippet(coche.getPrecioDia() + "€/día");
            
            if (carIcon != null) {
                marker.setIcon(carIcon);
            }

            // Al hacer clic en el marcador, abrir detalle
            marker.setOnMarkerClickListener((marker1, mapView1) -> {
                Intent intent = new Intent(getActivity(), DetalleCocheActivity.class);
                intent.putExtra("COCHE_DATA", coche);
                startActivity(intent);
                return true;
            });
            
            mapView.getOverlays().add(marker);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
