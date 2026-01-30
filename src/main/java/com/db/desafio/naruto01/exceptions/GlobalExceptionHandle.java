package com.db.desafio.naruto01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(PersonagemNaoEncontradoException.class)
    public ResponseEntity<Object> handlerPersonagemNaoEncontradoException(PersonagemNaoEncontradoException mensagem) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( mensagem.getMessage());
    }

  @ExceptionHandler(TipoNaoEncontradoException.class)
    public ResponseEntity<Object> handlerTipoNaoEncontradoException(TipoNaoEncontradoException mensagem) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( mensagem.getMessage());
    }
}