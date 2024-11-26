package com.parcial.app.models.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "Tratamientos")
public class Tratamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@NotEmpty
	@Column(name = "descripcion")
	private String descripcion;

	@NotEmpty
	@Column(name = "fecha_Inicio")
	private String fecha_Inicio;

	@NotEmpty
	@Column(name = "fecha_Fin")
	private String fecha_Fin;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha_Inicio() {
		return fecha_Inicio;
	}

	public void setFecha_Inicio(String fecha_Inicio) {
		this.fecha_Inicio = fecha_Inicio;
	}

	public String getFecha_Fin() {
		return fecha_Fin;
	}

	public void setFecha_Fin(String fecha_Fin) {
		this.fecha_Fin = fecha_Fin;
	}
	
	
	
	@ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;
	

    public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}



	@ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
	
	
	
	
}