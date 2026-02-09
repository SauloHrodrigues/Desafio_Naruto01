package com.db.desafio.naruto01.service;

import com.db.desafio.naruto01.dtos.AtacarDeJutsuRequest;
import com.db.desafio.naruto01.dtos.NovoJogoRequest;
import com.db.desafio.naruto01.model.Personagem;

public interface JogoServiceI {
    void iniciarJogo(NovoJogoRequest dto);

    Personagem validaJogador(Long id);

    void usarJutsu(Long id, AtacarDeJutsuRequest dto);
}