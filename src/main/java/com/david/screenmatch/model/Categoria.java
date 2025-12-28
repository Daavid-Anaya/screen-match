package com.david.screenmatch.model;

public enum Categoria {
    ACCION("Action", "Accion"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    TERROR("Horror", "Terror"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen");

    private String categoriaOmdb;
    private String categoriaEsp;

    private Categoria(String categoriaOmdb, String categoriaEsp) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEsp = categoriaEsp;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
            
        }
        throw new IllegalArgumentException("No existe una categoría con el nombre: " + text);
    }

    public static Categoria fromStringEsp(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEsp.equalsIgnoreCase(text)) {
                return categoria;
            }
            
        }
        throw new IllegalArgumentException("No existe una categoría con el nombre: " + text);
    }
}