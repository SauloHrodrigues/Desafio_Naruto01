package com.db.desafio.naruto01.exceptions;

import com.db.desafio.naruto01.exceptions.especies.*;
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

    @ExceptionHandler(JutsuNaoEncontradoException.class)
    public ResponseEntity<Object> handlerJutsuNaoEncontradoException(JutsuNaoEncontradoException mensagem) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( mensagem.getMessage());
    }

  @ExceptionHandler(TipoNaoEncontradoException.class)
    public ResponseEntity<Object> handlerTipoNaoEncontradoException(TipoNaoEncontradoException mensagem) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( mensagem.getMessage());
    }

    @ExceptionHandler(JogoNaoEncontradoException.class)
    public ResponseEntity<Object> handlerJogoNaoEncontradoException(JogoNaoEncontradoException mensagem) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( mensagem.getMessage());
    }

    @ExceptionHandler(ChakraInsuficentesException.class)
    public ResponseEntity<Object> handlerChakraInsuficentesException(ChakraInsuficentesException mensagem) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body( mensagem.getMessage());
    }

    @ExceptionHandler(JogadaInvalidaException.class)
    public ResponseEntity<Object> handlerJogadaInvalidaException(JogadaInvalidaException mensagem) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body( mensagem.getMessage());
    }
}