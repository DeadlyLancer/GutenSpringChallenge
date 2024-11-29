package com.aluracurso.retoliteratura.challenge.repos;

import java.util.List;

import com.aluracurso.retoliteratura.challenge.info.SGAutores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepo extends JpaRepository<SGAutores, Long> {

    @Query("SELECT a FROM SGAutores a WHERE :ano between a.fechaNacimiento AND a.fechaFallecimiento")
    List<SGAutores> findForYear(@Param("ano") int ano);

}
