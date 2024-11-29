package com.aluracurso.retoliteratura.challenge.info;

import com.aluracurso.retoliteratura.challenge.herramientas.Limitador;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class SGAutores  {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nombre;

@Column(name = "fecha_nacimiento")
private Integer fechaNacimiento = 0;

@Column(name = "fecha_fallecimiento")
private Integer fechaFallecimiento = 0;

@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
private List<SGLibros> libros;

public SGAutores() {}

// Constructor con el objeto Autor
public SGAutores(Autores autor) {
    this.nombre = Limitador.limite(autor.name(), 200);

    if (autor.birthYear() == null)
        this.fechaNacimiento = 1980;
    else
        this.fechaNacimiento = autor.birthYear();

    if (autor.deathYear() == null)
        this.fechaFallecimiento = 3022;
    else
        this.fechaFallecimiento = autor.deathYear();
}

// Getters y Setters
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public Integer getFechaNacimiento() {
    return fechaNacimiento;
}

public void setFechaNacimiento(Integer fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
}

public Integer getFechaFallecimiento() {
    return fechaFallecimiento;
}

public void setFechaFallecimiento(Integer fechaFallecimiento) {
    this.fechaFallecimiento = fechaFallecimiento;
}

public List<SGLibros> getLibros() {
    return libros;
}

public void setLibros(List<SGLibros> libros) {
    this.libros = libros;
}

@Override
public String toString() {
    return "AutorEntity [id=" + id + ", nombre=" + nombre
            + ", fechaNacimiento=" + fechaNacimiento
            + ", fechaFallecimiento=" + fechaFallecimiento
            + ", libros=" + (libros != null ? libros.size() : 0) + " libros]";
}
}
