package com.db.desafio.naruto01.service;

import com.db.desafio.naruto01.dtos.JutsuResponse;
import com.db.desafio.naruto01.dtos.NovoJutsu;
import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;

public interface PersonagemServiceI {

    PersonagemResponse novoPersonagem(NovoPersonagem dto);

    JutsuResponse adicionarNovoJutsu(Long id, NovoJutsu novo);

    void aumentarChakra(Long id, int chakras);
}
