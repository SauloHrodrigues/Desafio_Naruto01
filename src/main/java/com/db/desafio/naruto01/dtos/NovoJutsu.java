package com.db.desafio.naruto01.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para cadastro de um novo jutsu")
public record NovoJutsu(

        @Schema(description = "Nome do jutsu a ser cadastrado",example = "Chidori",
                requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
        @NotBlank(message = "O nome do jutsu é campo de preenchimento obrigatório")
        String nomeDoJutsu
) {}