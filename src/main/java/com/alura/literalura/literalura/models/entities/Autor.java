package com.alura.literalura.literalura.models.entities;

import java.util.Set;

import com.alura.literalura.literalura.models.dtos.AutorDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private Set<Libro> libros;

    public Autor() {
    }

    public Autor(AutorDto autorDto) {
        this.nombre = autorDto.nombre();
        this.anioNacimiento = autorDto.anioNacimiento();
        this.anioMuerte = autorDto.anioMuerte();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        libros.forEach(l -> l.getAutores());
        this.libros = libros;
    }

    // @Override
    // public String toString() {
    // return "\n ---- Autor ---- " +
    // "\n Nombre: " + nombre +
    // "\n Fecha de Nacimiento: " + anioNacimiento +
    // "\n Fecha de Fallecimiento: " + anioMuerte ;
    // }
    @Override
    public String toString() {
        return "\n  ---- Autor ---- " +
                "\n Nombre: " + nombre +
                "\n Fecha de Nacimiento: " + anioNacimiento +
                "\n Fecha de Fallecimiento: " + anioMuerte +
                "\n Libros: " + libros;
    }

}
