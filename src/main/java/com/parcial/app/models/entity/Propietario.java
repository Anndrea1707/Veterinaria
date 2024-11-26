package com.parcial.app.models.entity;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Propietarios")
public class Propietario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty
	@Column(name = "direccion")
	private String direccion;
	
	@NotNull
	@Column(name = "nummascotas")
	private Integer nummascotas;


	@NotNull
	@Column(name = "telefono")
	private Integer telefono;
	
	@Column(unique = true, nullable = false)
    private String email;
	
	

	public Integer getNummascotas() {
	    return nummascotas;
	}

	public void setNummascotas(Integer nummascotas) {
	    this.nummascotas = nummascotas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	
	@OneToMany(mappedBy = "propietario")
    private List<Mascota> mascotas;
	
	
	

}
