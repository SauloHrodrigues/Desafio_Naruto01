package com.db.desafio.naruto01.dtos;

import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.Jutsu;

import java.util.List;

public record PersonagemResponse(
        Long id,
        String nome,
        int idade,
        String aldeia,
        List<Jutsu> jutsus,
        TipoDeNinja tipoDeNinja,
        int chakra
) {
}
