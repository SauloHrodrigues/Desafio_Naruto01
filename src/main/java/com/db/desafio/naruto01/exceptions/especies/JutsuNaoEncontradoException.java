package com.db.desafio.naruto01.exceptions.especies;

public class JutsuNaoEncontradoException extends RuntimeException{
    public JutsuNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}