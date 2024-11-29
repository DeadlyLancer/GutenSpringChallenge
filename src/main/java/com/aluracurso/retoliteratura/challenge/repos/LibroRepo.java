package com.aluracurso.retoliteratura.challenge.repos;

import java.util.List;

import com.aluracurso.retoliteratura.challenge.info.SGLibros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aluracurso.retoliteratura.challenge.info.SGLibros;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepo extends JpaRepository<SGLibros, Long> {

    @Query("SELECT l FROM SGLibros l WHERE l.lenguaje >= :idioma")
    List<SGLibros> findForLanguaje(String idioma);

}
