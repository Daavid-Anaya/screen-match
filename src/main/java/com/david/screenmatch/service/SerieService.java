package com.david.screenmatch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.screenmatch.dto.SerieDTO;
import com.david.screenmatch.model.Serie;
import com.david.screenmatch.repository.SerieRepository;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerTodasLasSeries() {
        return convierteDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }   

    public List<SerieDTO> convierteDatos(List<Serie> serie) {
        return serie.stream().map(s -> new SerieDTO(
            s.getTitulo(),
            s.getTotalTemporadas(),
            s.getEvaluacion(),
            s.getPoster(),
            s.getGenero(),
            s.getActores(),
            s.getSinopsis()
        )).collect(Collectors.toList());
    }
}
