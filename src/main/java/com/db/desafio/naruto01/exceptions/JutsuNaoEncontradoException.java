package com.db.desafio.naruto01.exceptions;

public class JutsuNaoEncontradoException extends RuntimeException{
    public JutsuNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}