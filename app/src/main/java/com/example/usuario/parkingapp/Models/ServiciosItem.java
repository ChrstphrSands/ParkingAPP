package com.example.usuario.parkingapp.Models;

import com.google.gson.annotations.SerializedName;

public class ServiciosItem{

	@SerializedName("descripcion")
	private String descripcion;

	@SerializedName("costo")
	private double costo;

	@SerializedName("esPorHora")
	private String esPorHora;

	@SerializedName("id")
	private int id;

	@SerializedName("cochera_id")
	private String cocheraId;

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setCosto(double costo){
		this.costo = costo;
	}

	public double getCosto(){
		return costo;
	}

	public void setEsPorHora(String esPorHora){
		this.esPorHora = esPorHora;
	}

	public String getEsPorHora(){
		return esPorHora;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCocheraId(String cocheraId){
		this.cocheraId = cocheraId;
	}

	public String getCocheraId(){
		return cocheraId;
	}

	@Override
 	public String toString(){
		return 
			"ServiciosItem{" + 
			"descripcion = '" + descripcion + '\'' + 
			",costo = '" + costo + '\'' + 
			",esPorHora = '" + esPorHora + '\'' + 
			",id = '" + id + '\'' + 
			",cochera_id = '" + cocheraId + '\'' + 
			"}";
		}
}