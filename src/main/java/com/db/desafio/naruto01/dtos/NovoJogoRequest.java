package com.db.desafio.naruto01.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "DTO para iniciar novo jogo")
public record NovoJogoRequest(
        @NotNull(message = "A lista de personagens é obrigatória")
        @Size(min = 2, message = "É necessário informar no mínimo 2 ids de personagens para iniciar o jogo")
        @Schema(
                description = "IDs dos personagens que participarão do jogo (mínimo de 2)",
                example = "[1, 2]"
        )
        List<Long> ids
) {}