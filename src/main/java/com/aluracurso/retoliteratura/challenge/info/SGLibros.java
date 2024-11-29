package com.aluracurso.retoliteratura.challenge.info;

import com.aluracurso.retoliteratura.challenge.herramientas.Limitador;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class SGLibros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String lenguaje;
    private Integer descargas;

    @ManyToOne(fetch = FetchType.LAZY)  // Consider changing to LAZY if not needed
    @JoinColumn(name="autor_id")  // Consider changing to LAZY if not needed
    private SGAutores autor;

    public SGLibros() {
        // Default constructor
    }

    public SGLibros(Libros libro) {
        this.titulo = Optional.ofNullable(libro.title())
                .map(title -> Limitador.limite(title, 200))
                .orElse("");
        this.descargas = Optional.ofNullable(libro.download()).orElse(0);

        // Set language if available
        this.lenguaje = Optional.ofNullable(libro.languages())
                .filter(languages -> !languages.isEmpty())
                .map(languages -> languages.get(0))
                .orElse("");

        // Set the first author if available
        this.autor = Optional.ofNullable(libro.autores())
                .filter(authors -> !authors.isEmpty())
                .map(authors -> new SGAutores(authors.get(0)))
                .orElse(null);  // Could be null if no author exists
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return String.format("SGLibros [id=%d, titulo=%s, lenguaje=%s, descargas=%d, autor=%s]",
                id, titulo, lenguaje, descargas, autor);
    }

    public SGAutores getAutor() {
        return autor;
    }

    public void setAutor(SGAutores autor) {
        this.autor = autor;
    }

}

