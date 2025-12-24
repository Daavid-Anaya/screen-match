package com.david.screenmatch.model;

public enum Categoria {
    ACCION("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    TERROR("Horror"),
    DRAMA("Drama"),
    CRIMEN("Crime");

    private String categoriaOmdb;

    private Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
            
        }
        throw new IllegalArgumentException("No existe una categoría con el nombre: " + text);
    }
}