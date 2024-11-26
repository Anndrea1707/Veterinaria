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
@Table(name = "Veterinarios")
public class Veterinario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty
	@Column(name = "especialidad")
	private String especialidad;

	@NotNull
	@Column(name = "telefono")
	private Integer telefono;
	
	
	

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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	
	@OneToMany(mappedBy = "veterinario")
    private List<Cita> citas;

    @OneToMany(mappedBy = "veterinario")
    private List<Tratamiento> tratamientos;
	
	

}
