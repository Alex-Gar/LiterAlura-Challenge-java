package com.alura.literalura.literalura.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.literalura.literalura.models.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l JOIN l.autores a")
    List<Libro> listarLibrosRegistrados();

    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE lenguas = :lenguas")
    List<Libro> buscarLibrosPorIdioma(List<String> lenguas);

}
