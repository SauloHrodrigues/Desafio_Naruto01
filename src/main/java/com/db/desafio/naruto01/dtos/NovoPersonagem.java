package com.db.desafio.naruto01.dtos;

import com.db.desafio.naruto01.annotations.ValidarTipoDeNinja;
import com.db.desafio.naruto01.enuns.TipoDeNinja;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

@Schema(description = "DTO para cadastro de um novo jutsu")
public record NovoPersonagem(

        @NotBlank(message = "O nome é campo de preenchimento obrigatório")
        @Schema(description = "Nome do personagem", example = "Naruto Uzumaki", requiredMode = Schema.RequiredMode.REQUIRED)
        String nome,

        @Min(value = 1, message = "A idade deve ser maior que 0")
        @Schema(description = "Idade do personagem", example = "17", requiredMode = Schema.RequiredMode.REQUIRED)
        int idade,

        @NotBlank(message = "A aldeia é campo de preenchimento obrigatória")
        @Schema(description = "Aldeia do personagem", example = "Konoha", requiredMode = Schema.RequiredMode.REQUIRED)
        String aldeia,

        @Schema(
                description = "Mapa de jutsus do personagem (chave = nome do jutsu, valor = dano máximo)",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        Map<String, Integer> jutsus,

        @NotNull(message = "O tipo de ninja é campo de preenchimento obrigatório")
        @Schema(description = "Tipo de ninja", example = "NINJATAIJUTSU", requiredMode = Schema.RequiredMode.REQUIRED)
        TipoDeNinja tipoDeNinja,

        @Min(value = 0, message = "O chakra deve ser 0 ou maior")
        @Schema(description = "Quantidade de chakra do personagem", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
        int chakra
) {}