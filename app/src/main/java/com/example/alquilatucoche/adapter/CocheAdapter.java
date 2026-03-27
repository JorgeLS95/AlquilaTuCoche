package com.example.alquilatucoche.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquilatucoche.R;
import com.example.alquilatucoche.model.Coche;

import java.util.List;

public class CocheAdapter extends RecyclerView.Adapter<CocheAdapter.CocheViewHolder> {

    private List<Coche> listaCoches;

    public CocheAdapter(List<Coche> listaCoches) {
        this.listaCoches = listaCoches;
    }

    @NonNull
    @Override
    public CocheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coche, parent, false);
        return new CocheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocheViewHolder holder, int position) {
        Coche coche = listaCoches.get(position);
        holder.tvDescripcion.setText(coche.getDescripcion());
        holder.ivCoche.setImageResource(coche.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return listaCoches.size();
    }

    public static class CocheViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCoche;
        TextView tvDescripcion;

        public CocheViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCoche = itemView.findViewById(R.id.ivCoche);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionCoche);
        }
    }
}
