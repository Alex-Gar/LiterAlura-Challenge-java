package com.alura.literalura.literalura.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literalura.literalura.models.entities.Libro;
import com.alura.literalura.literalura.models.repository.LibroRepository;
import com.alura.literalura.literalura.services.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public String guardarLibro(Libro libro) {
        Object validacion = libroRepository.save(libro);
        if (validacion != null) {
            return "Libro Guardado correctamente.";
        }
        return "Error al agregar el libro.";

    }

    @Override
    public List<Libro> listarLibrosRegistrados() {
        List<Libro> listaLibros = libroRepository.listarLibrosRegistrados();
        return listaLibros;
    }

    @Override
    public List<Libro> buscarLibrosPorIdioma(List<String> lenguas) {
        List<Libro> listaLibros = libroRepository.buscarLibrosPorIdioma(lenguas);
        return listaLibros;
    }

}
