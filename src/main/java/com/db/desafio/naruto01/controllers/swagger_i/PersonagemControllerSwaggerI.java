package com.db.desafio.naruto01.controllers.swagger_i;

import com.db.desafio.naruto01.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Personagens", description = "Operações relacionadas aos personagens")
public interface PersonagemControllerSwaggerI {


//    @Operation(summary = "Cadastrar novo personagem",
//            description = "Cria um novo personagem no sistema"
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "201", description = "Personagem criado com sucesso"),
//            @ApiResponse(responseCode = "400", description = "Dados inválidos")
//    })
//    ResponseEntity<PersonagemResponse> cadastrar(
//            @RequestBody NovoPersonagem dto
//    );
//
//
//    @Operation(
//            summary = "Adicionar jutsu a um personagem existente",
//            description = "Adiciona um novo jutsu a um personagem existente"
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Jutsu adicionado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
//    })
//    ResponseEntity<JutsuResponse> adicionaJutsu(
//            @Parameter(description = "ID do personagem", example = "1")
//            @PathVariable Long id,
//            @RequestBody NovoJutsu novoJutsu
//    );
//
//    @Operation(
//            summary = "Adicionar chakra",
//            description = "Aumenta a quantidade de chakra do personagem"
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "204", description = "Chakra adicionado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
//    })
//    ResponseEntity<Void> adicionaChakra(
//            @Parameter(description = "ID do personagem", example = "1")
//            @PathVariable Long id,
//            @Parameter(description = "Quantidade de chakra a adicionar", example = "50")
//            @RequestParam int chakras
//    );
//
//
//    @Operation(
//            summary = "Usar jutsu (atacar)",
//            description = "Personagem utiliza um jutsu para atacar"
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Ataque realizado"),
//            @ApiResponse(responseCode = "400", description = "Sem chakra suficiente")
//    })
//    ResponseEntity<String> usarJutsu(
//            @Parameter(description = "ID do personagem", example = "1")
//            @PathVariable Long id
//    );
//
//
//    @Operation(
//            summary = "Desviar de ataque",
//            description = "Personagem tenta desviar de um ataque"
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Desvio realizado")
//    })
//    ResponseEntity<String> desviar(
//            @Parameter(description = "ID do personagem", example = "1")
//            @PathVariable Long id
//    );
//
//
//    @Operation(
//            summary = "Buscar personagem",
//            description = "Retorna os dados completos do personagem"
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Personagem encontrado"),
//            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
//    })
//    ResponseEntity<PersonagemExibirResponse> mostraPersonagem(
//            @Parameter(description = "ID do personagem", example = "1")
//            @PathVariable Long id
//    );
}