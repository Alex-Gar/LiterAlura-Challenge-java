package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alura.literalura.literalura.models.dtos.LibroDto;
import com.alura.literalura.literalura.models.entities.Autor;
import com.alura.literalura.literalura.models.entities.Libro;
import com.alura.literalura.literalura.services.LibroService;
import com.alura.literalura.literalura.services.implementations.ConsumoApiImpl;

import utils.ConvierteDatosImpl;

public class Principal {

    Scanner input = new Scanner(System.in);
    ConsumoApiImpl consumoApi = new ConsumoApiImpl();
    private static final String URL_API = "https://gutendex.com/books/";
    private ConvierteDatosImpl transformarDatos = new ConvierteDatosImpl();
    private LibroService libroService;

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {

            System.out.println("*** Elija la opción a través de su número: ");

            System.out.println("1.- Buscar libro por titulo");
            System.out.println("2.- Listar libros registrados");
            System.out.println("3.- Listar autores registrados ");
            System.out.println("4.- Listar autores vivos en un determinado año");
            System.out.println("5.-Listar libros por idioma");
            System.out.println("0.- Salir");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 0:

                    break;

                default:
                    break;
            }

        }
    }

    public Principal(LibroService libroService) {
        this.libroService = libroService;
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingresa el nombre del libro que desea buscar");
        String libroABuscar = input.nextLine();

        String request = this.consumoApi.ConsumoApi(URL_API + "?search=" + libroABuscar.replace(" ", "+"));

        List<LibroDto> listjson = transformarDatos.convierteDatos(request, LibroDto.class);

        Optional<LibroDto> libroEncontrado = listjson.stream()
                .filter(l -> l.titulo().equals(libroABuscar))
                .findFirst();

        if (libroEncontrado.isPresent()) {
            Libro libro = new Libro(libroEncontrado.get(), new ArrayList<>());

            List<Autor> autoresEntidad = libroEncontrado.get().autores().stream()
                    .map(autorDto -> {
                        Autor autor = new Autor(autorDto);
                        autor.setLibro(libro);
                        return autor;
                    }).collect(Collectors.toList());

            libro.setAutores(autoresEntidad);

            libroService.guardarLibro(libro);
        } else {
            System.out.println("Libro no encontrado.");
        }

    }

}
