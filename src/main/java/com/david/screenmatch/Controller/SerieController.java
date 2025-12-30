package com.david.screenmatch.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {
    @GetMapping("/series")
    public String mostrarMensaje() {
        return "Prueba exitosa de controlador REST";
    }
}
