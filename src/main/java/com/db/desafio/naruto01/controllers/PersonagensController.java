package com.db.desafio.naruto01.controllers;

import com.db.desafio.naruto01.controllers.swagger_i.PersonagemControllerSwaggerI;
import com.db.desafio.naruto01.dtos.*;
import com.db.desafio.naruto01.service.PersonagemServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/personagens")
public class PersonagensController implements PersonagemControllerSwaggerI {

    private final PersonagemServiceI serviceI;

    @PostMapping
    public ResponseEntity<PersonagemResponse> cadastrar(@RequestBody @Valid NovoPersonagem dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceI.novoPersonagem(dto));
    }

    @PostMapping("/{id}/jutsus")
    public ResponseEntity<Void> adicionaJutsu(@PathVariable Long id, @RequestBody @Valid NovoJutsu novoJutsu){
        serviceI.adicionarNovoJutsu(id,novoJutsu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/chakras")
    public ResponseEntity<Void> adicionaChakra(@PathVariable Long id, @RequestParam int chakra){
        serviceI.aumentarChakra(id, chakra);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/ataques")
    public ResponseEntity<String> usarJutsu(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.usarJutsu(id));
    }

    @GetMapping("/{id}/desviar")
    public ResponseEntity<String> desviar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.desviar(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemExibirResponse> mostraPersonagem(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.exibirPersonagem(id));
    }
}