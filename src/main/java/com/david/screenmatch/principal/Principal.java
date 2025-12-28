package com.david.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.david.screenmatch.model.Categoria;
import com.david.screenmatch.model.DatosSerie;
import com.david.screenmatch.model.DatosTemporada;
import com.david.screenmatch.model.Episodio;
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
    private List<Serie> series;
    //private List<DatosSerie> datosSeries = new ArrayList<>();

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
                    4 - Buscar serie por titulo      
                    5 - Mostrar Top 5 mejores series
                    6 - Buscar series por categoría
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
                case 4:
                    buscarSeriesPorTitulo();
                    break;
                case 5:
                    buscarTop5Series();
                    break;
                case 6:
                    buscarSeriesPorCategoria();
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
        //DatosSerie datosSerie = getDatosSerie();
        mostrarSeriesBuscadas();
        System.out.println("Escribe el nombre de la serie para buscar sus episodios:");
        var nombreSerie = scanner.nextLine();

        Optional<Serie> serie = series.stream()
            .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
            .findFirst();
        
        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DatosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                .flatMap(d -> d.episodios().stream()
                    .map(e -> new Episodio(d.numeroTemporada(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            serieRepository.save(serieEncontrada);
        }
    }

    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        serieRepository.save(serie);
        //datosSeries.add(datos);
        System.out.println(datos);
    }

    private void mostrarSeriesBuscadas() {
        series = serieRepository.findAll();

        series.stream()
            .sorted(Comparator.comparing(Serie::getGenero))
            .forEach(System.out::println);
    }

    private void buscarSeriesPorTitulo() {
        System.out.print("Escribe el nombre de la serie que desea buscar: ");
        var nombreSerie = scanner.nextLine();

        Optional<Serie> serieBuscada = serieRepository.findByTituloContainsIgnoreCase(nombreSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("La serie buscada es: " + serieBuscada.get());
        } else {
            System.out.println("Serie no encontrada.");
        }
    }

    private void buscarTop5Series() {
        List<Serie> top5Series = serieRepository.findTop5ByOrderByEvaluacionDesc();

        top5Series.forEach(
            s -> System.out.println("Serie: " + s.getTitulo() + " - Evaluación: " + s.getEvaluacion())
        );
    }

    private void buscarSeriesPorCategoria() {
        System.out.print("Escribe el género/categoría de la serie que desea buscar: ");
        var genero = scanner.nextLine();
        var categoria = Categoria.fromStringEsp(genero);
        List<Serie> seriesPorCategoria = serieRepository.findByGenero(categoria);

        System.out.println("Series encontradas en la categoría " + genero + ": ");
        seriesPorCategoria.forEach(System.out::println);
    }
}