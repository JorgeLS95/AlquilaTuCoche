package com.example.alquilatucoche.fragment;

import android.content.Context;
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
        String[] modelos = {"Seat Arona", "Volkswagen Golf", "Toyota Corolla", "Ford Focus", "Seat Ibiza"};
        Random random = new Random();
        
        // Icono personalizado para el marcador (el coche que creaste)
        Drawable carIcon = ContextCompat.getDrawable(getContext(), R.drawable.coche);

        for (int i = 0; i < 8; i++) {
            // Generar desplazamiento aleatorio (aprox 1-2km a la redonda)
            double randomLat = lat + (random.nextDouble() - 0.5) / 50;
            double randomLon = lon + (random.nextDouble() - 0.5) / 50;
            
            GeoPoint carPoint = new GeoPoint(randomLat, randomLon);
            String modelo = modelos[random.nextInt(modelos.length)];

            Marker marker = new Marker(mapView);
            marker.setPosition(carPoint);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setTitle(modelo + " disponible");
            
            // Usar tu icono de coche personalizado en el mapa
            if (carIcon != null) {
                marker.setIcon(carIcon);
            }
            
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
