package com.db.desafio.naruto01.exceptions;

public class TipoNaoEncontradoException extends RuntimeException{
    public TipoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
