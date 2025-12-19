package com.david.screenmatch.model;

public enum Categoria {
    ACCION("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    TERROR("Horror"),
    DRAMA("Drama"),
    CRIMEN("Crime");

    @SuppressWarnings("unused")
    private String categoriaOmdb;

    private Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }
}