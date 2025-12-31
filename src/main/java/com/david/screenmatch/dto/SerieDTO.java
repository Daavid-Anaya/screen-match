package com.david.screenmatch.dto;

import com.david.screenmatch.model.Categoria;

public record SerieDTO(
    Long id,
    String titulo,
    Integer totalTemporadas,
    Double evaluacion,
    String poster,
    Categoria genero,
    String actores,
    String sinopsis
) { 

}