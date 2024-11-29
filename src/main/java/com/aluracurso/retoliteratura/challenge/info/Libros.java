package com.aluracurso.retoliteratura.challenge.info;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libros(
        @JsonAlias("id") String id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<Autores> autores,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer download
) {
}
