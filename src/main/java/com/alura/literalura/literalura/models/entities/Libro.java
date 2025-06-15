package com.alura.literalura.literalura.models.entities;

import java.io.Serializable;
import java.util.List;

import com.alura.literalura.literalura.models.dtos.LibroDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;
    private List<String> lenguas;
    private String numeroDescargas;

    public Libro() {
    }

    public Libro(LibroDto libroDto, List<Autor> autorDto) {
        this.id = libroDto.id();
        this.titulo = libroDto.titulo();
        this.autores = autorDto;
        this.lenguas = libroDto.lenguas();
        this.numeroDescargas = libroDto.numeroDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(a -> a.setLibro(this));
        this.autores = autores;
    }

    public List<String> getLenguas() {
        return lenguas;
    }

    public List<String> isLenguas() {
        return lenguas;
    }

    public void setLenguas(List<String> lenguas) {
        this.lenguas = lenguas;
    }

    public String getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(String numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

}
