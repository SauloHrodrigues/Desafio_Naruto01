package com.db.desafio.naruto01.exceptions;

public class JogoNaoEncontradoException extends RuntimeException{
    public JogoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}