package com.db.desafio.naruto01.controllers.swagger_i;

import com.db.desafio.naruto01.dtos.AtacarDeJutsuRequest;
import com.db.desafio.naruto01.dtos.NovoJogoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Jogo", description = "Endpoint para gerenciar o jogo.")
public interface JogoControllerSwagger {

    @Operation(
            summary = "Iniciar um novo jogo",
            description = "Inicializa uma partida adicionando os personagens informados ao jogo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo iniciado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos informados",
                    content = @Content(schema = @Schema(example = "Lista de personagens inválida"))),
            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    })
    ResponseEntity<Void> iniciarJogo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Lista de IDs dos personagens que participarão do jogo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NovoJogoRequest.class))
            )
            NovoJogoRequest dto
    );


    @Operation(
            summary = "Usar jutsu contra adversário",
            description = "Executa um ataque de jutsu de um personagem contra outro jogador do jogo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jutsu executado com sucesso",
                    content = @Content(schema = @Schema(example = "Rasengan executado com sucesso!"))),
            @ApiResponse(responseCode = "400", description = "Jogada inválida"),
            @ApiResponse(responseCode = "404", description = "Jogo ou personagem não encontrado")
    })
    ResponseEntity<String> usarJutsu(

            @Parameter(description = "ID do personagem atacante", example = "1", required = true)
            Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do ataque de jutsu",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AtacarDeJutsuRequest.class))
            )
            AtacarDeJutsuRequest dto
    );


    @Operation(
            summary = "Desviar de um ataque",
            description = "Permite que o jogador tente desviar do ataque atual. O sucesso depende de chance randômica."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado da tentativa de defesa",
                    content = @Content(schema = @Schema(example = "Desvio realizado com sucesso!"))),
            @ApiResponse(responseCode = "400", description = "Não há defesa ativa ou jogada inválida"),
            @ApiResponse(responseCode = "404", description = "Jogo não encontrado")
    })
    ResponseEntity<String> desviar(

            @Parameter(description = "ID do personagem que irá desviar", example = "2", required = true)
            Long id
    );
}

