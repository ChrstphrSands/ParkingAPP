package com.example.usuario.parkingapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cliente {

    @SerializedName("id")
    private int cliente_id;

    @SerializedName("Apellido")
    private String apellido;

    @SerializedName("Direccion")
    private String direccion;

    @SerializedName("Celular")
    private int celular;

    @SerializedName("Telefono")
    private int telefono;

    @SerializedName("Nombre")
    private String nombre;

    @SerializedName("DNI")
    private int DNI;

    @SerializedName("Email")
    private String email;

    @SerializedName("Vehiculo")
    private List<Vehiculo> vehiculos;

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getCelular() {
        return celular;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDNI(int dNI) {
        this.DNI = dNI;
    }

    public int getDNI() {
        return DNI;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    @Override
    public String toString() {
        return
            "Cliente{" +
                "apellido = '" + apellido + '\'' +
                ",direccion = '" + direccion + '\'' +
                ",celular = '" + celular + '\'' +
                ",telefono = '" + telefono + '\'' +
                ",nombre = '" + nombre + '\'' +
                ",DNI = '" + DNI + '\'' +
                ",email = '" + email + '\'' +
                ",vehiculos = '" + vehiculos + '\'' +
                "}";
    }
}