package com.example.alquilatucoche.model;

public class Reserva {
    private String cocheDescripcion;
    private String fecha;
    private double precio;

    public Reserva(String cocheDescripcion, String fecha, double precio) {
        this.cocheDescripcion = cocheDescripcion;
        this.fecha = fecha;
        this.precio = precio;
    }

    public String getCocheDescripcion() {
        return cocheDescripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public double getPrecio() {
        return precio;
    }
}
