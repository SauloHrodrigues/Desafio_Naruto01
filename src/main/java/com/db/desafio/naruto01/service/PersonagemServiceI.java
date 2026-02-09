package com.db.desafio.naruto01.service;

import com.db.desafio.naruto01.dtos.*;
import com.db.desafio.naruto01.model.Personagem;

public interface PersonagemServiceI {

    PersonagemResponse novoPersonagem(NovoPersonagem dto);

    void adicionarNovoJutsu(Long id, NovoJutsu novo);

    void aumentarChakra(Long id, int chakras);

    String usarJutsu(Long id);

    String desviar(Long id);

    PersonagemExibirResponse exibirPersonagem(Long id);

    void validarJutsuDoPersonagem(Personagem personagem,String jutsu);
    void validarJutsuDoPersonagem(Long id,String jutsu);

    Personagem buscarPersonagem(Long id);

    void diminuirChakra(Long id, int quantidade);
}