package com.db.desafio.naruto01.controllers;

import com.db.desafio.naruto01.dtos.AtacarDeJutsuRequest;
import com.db.desafio.naruto01.dtos.NovoJogoRequest;
import com.db.desafio.naruto01.service.JogoServiceI;
import com.db.desafio.naruto01.service.implementacao.JogoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoServiceImpl serviceI;

    @PostMapping
    public ResponseEntity<Void> iniciarJogo(@RequestBody NovoJogoRequest dto){
        serviceI.iniciarJogo(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/ataque")
    public ResponseEntity<Void> usarJutsu(@PathVariable Long id, @RequestBody AtacarDeJutsuRequest dto){
        serviceI.usarJutsu(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/desvios")
    public ResponseEntity<String> desviar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.desviar(id));
    }
}
