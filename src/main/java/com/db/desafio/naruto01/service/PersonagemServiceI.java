package com.db.desafio.naruto01.service;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;

public interface PersonagemServiceI {

    PersonagemResponse novoPersona(NovoPersonagem dto);
}
