package com.aluracurso.retoliteratura.challenge;

import com.aluracurso.retoliteratura.challenge.menus.Opciones;
import com.aluracurso.retoliteratura.challenge.repos.AutorRepo;
import com.aluracurso.retoliteratura.challenge.repos.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Alpha1 implements CommandLineRunner {

    @Autowired
    private LibroRepo libroRepositorio;
    @Autowired
    private AutorRepo autorRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(Alpha1.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Opciones client = new Opciones(libroRepositorio, autorRepositorio);
        client.menu();
    }

}
