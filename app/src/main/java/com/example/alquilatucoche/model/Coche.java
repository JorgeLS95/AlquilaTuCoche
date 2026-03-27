package com.example.alquilatucoche.model;

public class Coche {
    private String descripcion;
    private int imagenResId; // Por ahora usaremos recursos locales

    public Coche(String descripcion, int imagenResId) {
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public void setImagenResId(int imagenResId) {
        this.imagenResId = imagenResId;
    }
}
