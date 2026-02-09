package com.db.desafio.naruto01.exceptions;

public class JogadaInvalidaException extends RuntimeException{
    public JogadaInvalidaException(String mensagem){
        super(mensagem);
    }
}