package com.alura.literalura.literalura.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.literalura.literalura.models.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
