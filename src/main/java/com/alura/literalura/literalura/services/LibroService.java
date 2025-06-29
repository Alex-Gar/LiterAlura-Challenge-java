package com.alura.literalura.literalura.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alura.literalura.literalura.models.entities.Libro;

@Service
public interface LibroService {

    String guardarLibro(Libro libro);

    List<Libro> listarLibrosRegistrados();

    List<Libro> buscarLibrosPorIdioma(List<String> lenguas);

}
