package com.crud.model;

import jakarta.persistence.*;

@Entity
public class DetallesAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String direccion;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String telefono;

    public DetallesAlumno() {}

    public DetallesAlumno(Long id, String direccion, String email, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
