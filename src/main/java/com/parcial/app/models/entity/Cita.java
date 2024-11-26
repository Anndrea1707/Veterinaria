package com.parcial.app.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "fecha")
    private String fecha;

    @NotEmpty
    @Column(name = "hora")
    private String hora;

    @NotEmpty
    @Column(name = "motivo")
    private String motivo;

    // Relación con Propietario
    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    // Relación con Mascota
    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    // Relación con Veterinario
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    // Relación con Tratamiento
    @ManyToOne
    @JoinColumn(name = "tratamiento_id")
    private Tratamiento tratamiento;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascotas) {
        this.mascota = mascotas;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinarios) {
        this.veterinario = veterinarios;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
}
