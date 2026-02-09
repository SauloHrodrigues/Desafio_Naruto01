package com.db.desafio.naruto01.enuns;

import com.db.desafio.naruto01.exceptions.TipoNaoEncontradoException;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    @JsonCreator
    public static TipoDeNinja fromString(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new TipoNaoEncontradoException("Tipo de ninja não informado.");
        }

        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(valor)
                        || t.getTipo().equalsIgnoreCase(valor))
                .findFirst()
                .orElseThrow(() -> new TipoNaoEncontradoException(
                        "Tipo de ninja inválido: '" + valor + "'. Valores válidos: " + valoresValidos()
                ));
    }

    public static String valoresValidos() {
        return Arrays.stream(values())
                .map(TipoDeNinja::name)
                .collect(Collectors.joining(", "));
    }
}