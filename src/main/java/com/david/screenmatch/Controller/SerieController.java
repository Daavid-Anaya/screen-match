package com.david.screenmatch.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.screenmatch.dto.SerieDTO;
import com.david.screenmatch.service.SerieService;

@RestController
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries() {
        return servicio.obtenerTodasLasSeries();
    }

    @GetMapping("/series/top5")
    public List<SerieDTO> obtenerTop5() {
        return servicio.obtenerTop5();
    }
}