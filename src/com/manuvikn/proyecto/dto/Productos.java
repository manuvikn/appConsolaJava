package com.manuvikn.proyecto.dto;

import java.sql.Date;

public class Productos {
	
	private String nombre;
	private String descripcion;
	private double precio;
	private Date fechaCompra;
	private long idProductos;
	
	public Productos() {
		
	}
	
	public Productos(String nombre, String descripcion, double precio, Date fechaCompra) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaCompra = fechaCompra;
	}
	
	public Productos(String nombre, String descripcion, double precio, Date fechaCompra, long idProductos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaCompra = fechaCompra;
		this.idProductos = idProductos;
	}

	public long getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(long idProductos) {
		this.idProductos = idProductos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	

}
