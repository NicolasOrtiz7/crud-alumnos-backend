package com.crud.model;

import jakarta.persistence.*;

@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String nombre;
    @Column(nullable = false)
    String apellido;
    @Column(nullable = false)
    int edad;
    @Column(nullable = false)
    int habilitado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalles_id")
    DetallesAlumno detallesAlumno;

    public Alumno() {
    }

    public Alumno(Long id, String nombre, String apellido, int edad, int habilitado, DetallesAlumno detallesAlumno) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.habilitado = habilitado;
        this.detallesAlumno = detallesAlumno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public DetallesAlumno getDetallesAlumno() {
        return detallesAlumno;
    }

    public void setDetallesAlumno(DetallesAlumno detallesAlumno) {
        this.detallesAlumno = detallesAlumno;
    }
}
