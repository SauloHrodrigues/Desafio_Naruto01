package com.db.desafio.naruto01.exceptions;

public class PersonagemNaoEncontradoException extends RuntimeException{
    public PersonagemNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}