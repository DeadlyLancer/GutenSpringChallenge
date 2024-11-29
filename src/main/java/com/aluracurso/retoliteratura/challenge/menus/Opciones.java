package com.aluracurso.retoliteratura.challenge.menus;

import com.aluracurso.retoliteratura.challenge.APIs.CodigoAPI;
import com.aluracurso.retoliteratura.challenge.info.Respuesta;
import com.aluracurso.retoliteratura.challenge.info.SGAutores;
import com.aluracurso.retoliteratura.challenge.info.SGLibros;
import com.aluracurso.retoliteratura.challenge.repos.AutorRepo;
import com.aluracurso.retoliteratura.challenge.repos.LibroRepo;
import com.aluracurso.retoliteratura.challenge.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Opciones {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private CodigoAPI consumoApi = new CodigoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepo libroRepositorio;
    private AutorRepo autorRepositorio;

    public Opciones(LibroRepo libroRepositorio, AutorRepo autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
					Elija la opción a traves de su número:
						1.- Buscar libro por titulo
						2.- Lista liibros registrados
						3.- Lista autores registrados
						4.- Lista autores vivos en un determinado año
						5.- Listar libros por idioma
						0 - Salir
						""";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    buscarLibros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresVivo();
                    break;
                case 5:
                    buscarPorIdiomas();
                    break;
                case 0:
                    System.out.println("Adios, Vuelva Pronto...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void buscarLibros() {

        List<SGLibros> libros = libroRepositorio.findAll();

        if (!libros.isEmpty()) {

            for (SGLibros libro : libros) {
                System.out.println("Libros");
                System.out.println(" Titulo: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombre());
                System.out.println(" Idioma: " + libro.getLenguaje());
                System.out.println(" Descargas: " + libro.getDescargas());
            }

        } else {
            System.out.println("Sin resultados de la busqueda");
        }

    }

    private void buscarAutores() {
        List<SGAutores> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (SGAutores autor : autores) {
                System.out.println("Autores");
                System.out.println(" Nombre: " + autor.getNombre());
                System.out.println(" Fecha de Nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de Fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Libros: " + autor.getLibros());
            }
        } else {
           System.out.println("Sin resultados de la busqueda");

        }

    }

    private void buscarAutoresVivo() {
        System.out.println("Escriba el año para en el que vivio: ");
        var anio = teclado.nextInt();
        teclado.nextLine();

        List<SGAutores> autores = autorRepositorio.findForYear(anio);

        if (!autores.isEmpty()) {
            for (SGAutores autor : autores) {
                System.out.println("Autores vivos");
                System.out.println(" Nombre: " + autor.getNombre());
                System.out.println(" Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Libros: " + autor.getLibros());
            }
        } else {
            System.out.println("Sin resultados de la busqueda");

        }
    }

    private void buscarPorIdiomas() {

        var menu = """
				   Seleccione un Idioma:
					1.- Español
					2.- Ingles
				
					""";
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String seleccion = "";

        if(idioma == 1) {
            seleccion = "es";
        } else 	if(idioma == 2) {
            seleccion = "en";
        }

        List<SGLibros> libros = libroRepositorio.findForLanguaje(seleccion);

        if (!libros.isEmpty()) {

            for (SGLibros libro : libros) {
                System.out.println("Libros filtrados por lenguaje");
                System.out.println(" Titulo: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombre());
                System.out.println(" Idioma: " + libro.getLenguaje());
                System.out.println(" Descargas: " + libro.getDescargas());
            }

        } else {
System.out.println("Sin resultados de la busqueda");
        }


    }

    private void buscarLibroWeb() {
        Respuesta datos = getDatosSerie();

        if (!datos.results().isEmpty()) {

            SGLibros libro = new SGLibros(datos.results().get(0));
            libro = libroRepositorio.save(libro);

        }

        System.out.println("Datos: ");
        System.out.println(datos);
    }

    private Respuesta getDatosSerie() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        var titulo = teclado.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Titlulo : " + titulo);
        System.out.println(URL_BASE + titulo);
        var json = consumoApi.getData(URL_BASE + titulo);
        System.out.println(json);
        Respuesta datos = conversor.obtenerDatos(json, Respuesta.class);
        return datos;
    }

}


