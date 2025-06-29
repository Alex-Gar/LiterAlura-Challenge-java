package com.alura.literalura.literalura.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alura.literalura.literalura.models.dtos.LibroDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "libro_autor", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores = new HashSet<>();

    private List<String> lenguas;
    private String numeroDescargas;

    public Libro() {
    }

    public Libro(LibroDto libroDto, Set<Autor> autorDto) {
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

    public void setAutores(Set<Autor> autores) {
        autores.forEach(a -> a.getLibros());
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

    @Override
    public String toString() {
        return "\n \t --- Libro ---- " +
                "\n \t Titulo: " + titulo +
                "\n \t Autores: " + (autores != null ? autores.stream()
                        .map(Autor::getNombre)
                        .collect(Collectors.joining(", "))
                        : "N/A") +
                "\n \t Lenguas: " + lenguas +
                "\n \t Numero de descargas: " + numeroDescargas +
                "\n \t --------------- \n";
    }

    public Set<Autor> getAutores() {
        return autores;
    }

}
