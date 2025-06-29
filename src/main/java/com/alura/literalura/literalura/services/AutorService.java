package com.alura.literalura.literalura.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alura.literalura.literalura.models.entities.Autor;

@Service
public interface AutorService {
      List<Autor> listarAutoresRegistrados();

      void guardarAutor(Autor autor);

      List<Autor> buscarPorAnioNacimiento(Integer anioNacimiento);

}
