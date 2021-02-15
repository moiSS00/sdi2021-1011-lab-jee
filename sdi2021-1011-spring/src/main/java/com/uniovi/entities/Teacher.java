package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
	
	@Id
	private String DNI; 
	private String nombre; 
	private String apellidos; 
	private String categoria; 
	
	public Teacher(String dNI, String nombre, String apellidos, String categoria) {
		super();
		this.DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoria = categoria;
	}

	public Teacher () {
		
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Teacher [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", categoria=" + categoria
				+ "]";
	}
	
	
	

}
