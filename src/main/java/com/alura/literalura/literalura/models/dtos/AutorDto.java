package com.alura.literalura.literalura.models.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDto(
                @JsonAlias("name") String nombre,
                @JsonAlias("birth_year") Integer anioNacimiento,
                @JsonAlias("death_year") Integer anioMuerte) {

}
