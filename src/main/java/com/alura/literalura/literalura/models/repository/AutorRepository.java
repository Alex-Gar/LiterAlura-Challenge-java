package com.alura.literalura.literalura.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.literalura.literalura.models.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a JOIN a.libros l ")
    List<Autor> listarAutoresRegistrados();

    @Query("SELECT a FROM Autor a JOIN a.libros l WHERE a.anioNacimiento = :anioNacimiento")
    List<Autor> buscarPorAnioNacimiento( Integer anioNacimiento);

}
