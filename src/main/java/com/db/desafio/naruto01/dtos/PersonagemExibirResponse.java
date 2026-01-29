package com.db.desafio.naruto01.dtos;

import java.util.List;

public record PersonagemExibirResponse(
        String nome,
        int idade,
        String aldeia,
        int chakra,
        List<JutsuResponse> jutsus
) {
}
