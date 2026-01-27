package com.db.desafio.naruto01.dtos;

import com.db.desafio.naruto01.interfaces.TipoDeNinja;

public record PersonagemResponse(
        Long id,
        String nome,
        int idade,
        String aldeia,
        String jutsu,
        TipoDeNinja tipoDeNinja,
        int chakra
) {
}
