package com.db.desafio.naruto01.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para realizar um ataque de jutsu")
public record AtacarDeJutsuRequest(
        @NotNull(message = "O ID do personagem adversário é de preenchimento obrigatório.")
        @Schema(description = "ID do personagem adversário, contra quem ataca.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long idAdversario,

        @NotBlank(message = "O nome do jutsu é obrigatório")
        @Schema(description = "Nome do jutsu utilizado no ataque", example = "RASENGAN")
        String jutsu
) {}