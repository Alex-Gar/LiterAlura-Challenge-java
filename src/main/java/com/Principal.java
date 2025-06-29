package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import com.alura.literalura.literalura.models.dtos.LibroDto;
import com.alura.literalura.literalura.models.entities.Autor;
import com.alura.literalura.literalura.models.entities.Libro;
import com.alura.literalura.literalura.services.AutorService;
import com.alura.literalura.literalura.services.LibroService;
import com.alura.literalura.literalura.services.implementations.ConsumoApiImpl;

import utils.ConvierteDatosImpl;

public class Principal {

    Scanner input = new Scanner(System.in);
    ConsumoApiImpl consumoApi = new ConsumoApiImpl();
    private static final String URL_API = "https://gutendex.com/books/";
    private ConvierteDatosImpl transformarDatos = new ConvierteDatosImpl();
    private LibroService libroService;
    private AutorService autorService;

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
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    filtrarPorAnioNacimiento();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    break;

                default:
                    break;
            }
        }
    }

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    private void buscarLibroPorTitulo() {
        int seleccionLibro = 0;
        int indice = 1;
        boolean bandera = true;

        System.out.println("--- Ingresa el nombre del libro que desea buscar ---");
        String libroABuscar = input.nextLine();
        String request = this.consumoApi.ConsumoApi(URL_API + "?search=" + libroABuscar.replace(" ", "+"));

        List<LibroDto> listjson = transformarDatos.convierteDatos(request, LibroDto.class);

        System.out.println(" \n ---- Seleccione el numero de libro ----- ");
        while (bandera) {
            indice = 1;
            for (int i = 0; i < listjson.size(); i++) {
                System.out.println(indice + ".- " + listjson.get(i));
                indice += 1;
            }
            seleccionLibro = input.nextInt();
            input.nextLine();
            if (seleccionLibro != 0) {
                bandera = false;
            }
        }

        LibroDto libroSeleccionado = listjson.get(seleccionLibro - 1);

        // Convierte los AutorDto a Autor
        Set<Autor> autoresEntidad = libroSeleccionado.autores().stream()
                .map(Autor::new) // Usa el constructor que recibe un AutorDto
                .collect(Collectors.toSet());

        // Guarda los autores en la base de datos
        autoresEntidad.forEach(autorService::guardarAutor);

        // Crea la entidad Libro y asigna los autores
        Libro libro = new Libro(libroSeleccionado, autoresEntidad);

        // Guarda el libro
        libroService.guardarLibro(libro);
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroService.listarLibrosRegistrados();
        libros.forEach(System.out::println);

    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorService.listarAutoresRegistrados();
        autores.forEach(System.out::println);
    }

    private void filtrarPorAnioNacimiento() {
        System.out.println("--- Ingresa la fecha de nacimiento del autor que desea buscar ---");
        int anioNaciemintoAutor = input.nextInt();

        List<Autor> listaAutores = autorService.buscarPorAnioNacimiento(anioNaciemintoAutor);
        if (!listaAutores.isEmpty()) {
            listaAutores.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron autores con el año de nacimiento especificado.");
            return;
        }
    }

    private void listarLibrosPorIdioma() {
        StringBuilder concat = new StringBuilder();

        concat.append("\n ---- Selecciona el idioma ---- \n" +
                "\n es - Español" +
                "\n en - Inglés" +
                "\n fr - Francés" +
                "\n pt - Portugues");
        System.out.println(concat.toString());

        String seleccionIdioma = input.nextLine();
        List<String> idiomas = new ArrayList<>();
        idiomas.add(seleccionIdioma);
        List<Libro> listaLibros = libroService.buscarLibrosPorIdioma(idiomas);
        if (!listaLibros.isEmpty()) {
            listaLibros.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron autores con el año de nacimiento especificado.");
            return;
        }
    }
}
