package com.db.desafio.naruto01.controllers;

import com.db.desafio.naruto01.dtos.JutsuResponse;
import com.db.desafio.naruto01.dtos.NovoJutsu;
import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.service.PersonagemServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/personagens")
public class PersonagensController {

    private final PersonagemServiceI serviceI;

    @PostMapping
    public ResponseEntity<PersonagemResponse> cadastrar(@RequestBody NovoPersonagem dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceI.novoPersonagem(dto));
    }

    @PutMapping("/{id}/adicionar_jutsu")
    public ResponseEntity<JutsuResponse> adicionaJutsu(@PathVariable Long id, @RequestBody NovoJutsu novoJutsu){
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.adicionarNovoJutsu(id,novoJutsu));
    }

    @PutMapping("/{id}/chakra")
    public ResponseEntity<Void> adicionaChacra(@PathVariable Long id){

        return null;
    }

    @PutMapping("/{id}/atacar")
    public ResponseEntity<Void> atacar(@PathVariable Long id){

        return null;
    }

    @PutMapping("/{id}/defender")
    public ResponseEntity<Void> defender(@PathVariable Long id){

        return null;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Void> mostraPersonagem(@PathVariable Long id){

        return null;
    }

    /*

    específica.
    • Um método que permita exibir todas as informações do personagem (nome,
    idade, aldeia, jutsus e chakra).
     */

}
