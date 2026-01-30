package com.db.desafio.naruto01.dtos;

import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.Jutsu;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
@Schema(description = "Resposta com os dados do personnagem cadastrado")
public record PersonagemResponse(

        @NotNull
        @Schema(description = "ID do personagem", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,

        @NotBlank
        @Schema(description = "Nome do personagem", example = "Naruto Uzumaki", requiredMode = Schema.RequiredMode.REQUIRED)
        String nome,

        @Min(0)
        @Schema(description = "Idade do personagem", example = "17", requiredMode = Schema.RequiredMode.REQUIRED)
        int idade,

        @NotBlank
        @Schema(description = "Aldeia do personagem", example = "Konoha", requiredMode = Schema.RequiredMode.REQUIRED)
        String aldeia,

        @NotNull
        @Schema(description = "Tipo do ninja", example = "NINJA_DE_NINJUTSU", requiredMode = Schema.RequiredMode.REQUIRED)
        TipoDeNinja tipoDeNinja,

        @Min(0)
        @Schema(description = "Quantidade de chakra do personagem", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
        int chakra,

        @Valid
        @Schema(description = "Lista de jutsus do personagem", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        List<JutsuResponse> jutsus
) {}