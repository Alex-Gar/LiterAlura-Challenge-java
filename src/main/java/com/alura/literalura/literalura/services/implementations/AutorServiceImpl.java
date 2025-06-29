package com.alura.literalura.literalura.services.implementations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alura.literalura.literalura.models.entities.Autor;
import com.alura.literalura.literalura.models.repository.AutorRepository;
import com.alura.literalura.literalura.services.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> listarAutoresRegistrados() {
        List<Autor> listarAutores = autorRepository.listarAutoresRegistrados();
        if (!listarAutores.isEmpty()) {
            return listarAutores;
        } else {
            return null;
        }
    }

    @Override
    public void guardarAutor(Autor autor) {
        autorRepository.save(autor);
    }

    @Override
    public List<Autor> buscarPorAnioNacimiento(Integer anioNacimiento) {
        List<Autor> listaAutores = autorRepository.buscarPorAnioNacimiento(anioNacimiento);
        return listaAutores;
    }
}
