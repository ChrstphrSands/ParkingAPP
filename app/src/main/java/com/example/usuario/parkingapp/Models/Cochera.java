package com.example.usuario.parkingapp.Models;

import com.google.gson.annotations.SerializedName;

public class Cochera {

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("longitud")
    private double longitud;

    @SerializedName("latitud")
    private double latitud;

    @SerializedName("codigo_postal")
    private String codigoPostal;

    @SerializedName("foto")
    private String foto;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("nombre")
    private String nombre;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return
            "Cochera{" +
                "descripcion = '" + descripcion + '\'' +
                ",longitud = '" + longitud + '\'' +
                ",latitud = '" + latitud + '\'' +
                ",codigo_postal = '" + codigoPostal + '\'' +
                ",foto = '" + foto + '\'' +
                ",direccion = '" + direccion + '\'' +
                ",nombre = '" + nombre + '\'' +
                "}";
    }
}