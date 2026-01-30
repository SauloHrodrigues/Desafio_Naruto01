package com.db.desafio.naruto01.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta com os dados do jutsu cadastrado")
public record JutsuResponse(

        @Schema(description = "Identificador do jutsu no banco", example = "10")
        Long id,

        @Schema(description = "Nome do jutsu", example = "Rasengan")
        String nomeDoJutsu,

        @Schema(description = "ID do personagem dono do jutsu", example = "1")
        Long personagemId
) {}