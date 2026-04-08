package com.example.alquilatucoche.model;

import java.io.Serializable;

public class Coche implements Serializable {
    private String marca;
    private String modelo;
    private String descripcion;
    private double precioDia;
    private int imagenResId;

    public Coche(String marca, String modelo, String descripcion, double precioDia, int imagenResId) {
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precioDia = precioDia;
        this.imagenResId = imagenResId;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioDia() { return precioDia; }
    public int getImagenResId() { return imagenResId; }
    
    public String getNombreCompleto() {
        return marca + " " + modelo;
    }
}
