package com.db.desafio.naruto01.dtos;

public record JutsuResponse(
        Long id,
        String nomeDoJutsu,
        Long personagemId
) {
}
