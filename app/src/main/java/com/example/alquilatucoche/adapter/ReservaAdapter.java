package com.example.alquilatucoche.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquilatucoche.R;
import com.example.alquilatucoche.model.Reserva;

import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {

    private List<Reserva> listaReservas;

    public ReservaAdapter(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva, parent, false);
        return new ReservaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        Reserva reserva = listaReservas.get(position);
        holder.tvCoche.setText(reserva.getCocheDescripcion());
        holder.tvFecha.setText(reserva.getFecha());
        holder.tvPrecio.setText(reserva.getPrecio() + "€");
    }

    @Override
    public int getItemCount() {
        return listaReservas.size();
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder {
        TextView tvCoche, tvFecha, tvPrecio;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCoche = itemView.findViewById(R.id.tvReservaCoche);
            tvFecha = itemView.findViewById(R.id.tvReservaFecha);
            tvPrecio = itemView.findViewById(R.id.tvReservaPrecio);
        }
    }
}
