package com.david.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.david.screenmatch.model.DatosSerie;
import com.david.screenmatch.model.DatosTemporada;
import com.david.screenmatch.model.Serie;
import com.david.screenmatch.repository.SerieRepository;
import com.david.screenmatch.service.ConsumoAPI;
import com.david.screenmatch.service.ConvierteDatos;

public class Principal {
    private SerieRepository serieRepository;
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=92dd4ce1";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosSerie> datosSeries = new ArrayList<>();

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void mostrarMenu() {
        var opcion = -1;
        var menu = """
                    \n====== MENÚ SERIES ======
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas             
                    0 - Salir
                    """;

        while (opcion != 0) {
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    private void buscarEpisodioPorSerie() {
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
            var json = consumoApi.obtenerDatos(URL_BASE + datosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        serieRepository.save(serie);
        //datosSeries.add(datos);
        System.out.println(datos);
    }

    private void mostrarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = datosSeries.stream()
            .map(s -> new Serie(s))
            .collect(Collectors.toList());

        series.stream()
            .sorted(Comparator.comparing(Serie::getGenero))
            .forEach(System.out::println);
    }
}