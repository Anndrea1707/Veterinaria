package com.parcial.app.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "tipo") 
    private String tipo;

    @NotEmpty
    @Column(name = "raza")
    private String raza;

    @NotNull
    @Column(name = "edad")
    private Integer edad;

    @NotEmpty
    @Column(name = "sexo")
    private String sexo;

    // Relación con Propietario
    @ManyToOne
    @JoinColumn(name="propietario_id")
    private Propietario propietario;


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Propietario getPropietario() {
    	return propietario;
    }
    
    public void setPropietario(Propietario propietario) {
    	this.propietario = propietario;
    }
}
