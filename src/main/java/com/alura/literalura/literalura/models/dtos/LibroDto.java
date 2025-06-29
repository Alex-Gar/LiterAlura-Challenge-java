package com.alura.literalura.literalura.models.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDto(
                @JsonAlias("id") Long id,
                @JsonAlias("title") String titulo,
                @JsonAlias("authors") List<AutorDto> autores,
                @JsonAlias("languages") List<String> lenguas,
                @JsonAlias("download_count") String numeroDescargas) {

}
