package com.db.desafio.naruto01.enuns;

public enum TipoDeNinja {

    TAIJUTSU("Taijutsu"),
    GENJUTSU("Genjutsu"),
    NINJUTSU("Ninjutsu");

    private final String tipo;

    TipoDeNinja(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
