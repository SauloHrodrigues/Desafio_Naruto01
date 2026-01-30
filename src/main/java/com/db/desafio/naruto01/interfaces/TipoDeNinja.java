package com.db.desafio.naruto01.interfaces;

public enum TipoDeNinja {
    GENJUTSU("Genjutsu"),
    TAIJUTSU("Taijutsu"),
    NINJUTSU("Ninjutsu");

    private final String tipo;

    TipoDeNinja(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}