package com.db.desafio.naruto01.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

@Schema(description = "Resposta com os dados do personagem cadastrado")
public record PersonagemExibirResponse(
        @NotBlank
        @Schema(description = "Nome do personagem", example = "Naruto Uzumaki", requiredMode = Schema.RequiredMode.REQUIRED)
        String nome,

        @Min(0)
        @Schema(description = "Idade do personagem", example = "17", requiredMode = Schema.RequiredMode.REQUIRED)
        int idade,

        @NotBlank
        @Schema(description = "Aldeia do personagem", example = "Konoha", requiredMode = Schema.RequiredMode.REQUIRED)
        String aldeia,

        @Min(0)
        @Schema(description = "Quantidade de chakra do personagem", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
        int chakra,

        @Schema(
                description = "Mapa de jutsus do personagem (chave = nome do jutsu, valor = dano máximo)",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        Map<String, Integer> jutsus

) {}