package com.db.desafio.naruto01.exceptions;

import com.db.desafio.naruto01.enuns.TipoDeNinja;

public class TipoNaoEncontradoException extends RuntimeException{
    public TipoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}