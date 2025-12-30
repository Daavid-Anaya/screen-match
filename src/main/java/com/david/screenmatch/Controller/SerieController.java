package com.david.screenmatch.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.screenmatch.dto.SerieDTO;
import com.david.screenmatch.repository.SerieRepository;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository repository;

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries() {
        return repository.findAll().stream().map(s -> new SerieDTO(
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