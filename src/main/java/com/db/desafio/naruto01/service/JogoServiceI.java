package com.db.desafio.naruto01.service;

import com.db.desafio.naruto01.dtos.AtacarDeJutsuRequest;
import com.db.desafio.naruto01.dtos.NovoJogoRequest;
import com.db.desafio.naruto01.exceptions.especies.JogadaInvalidaException;
import com.db.desafio.naruto01.exceptions.especies.JogoNaoEncontradoException;
import com.db.desafio.naruto01.model.Personagem;
import com.db.desafio.naruto01.service.implementacao.Jogo;

import java.util.Objects;
import java.util.Random;

public interface JogoServiceI {
    void iniciarJogo(NovoJogoRequest dto);

    String usarJutsu(Long id, AtacarDeJutsuRequest dto);

    String desviar(Long id);

    void validarDefesa(Long id);

    boolean respostaRandimica();

    void validaJogo();
}