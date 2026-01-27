package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.mapper.PersonagemMapper;
import com.db.desafio.naruto01.model.*;
import com.db.desafio.naruto01.repository.PersonagemRepository;
import com.db.desafio.naruto01.service.PersonagemServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonagemSeviceImpl implements PersonagemServiceI {
    private final PersonagemRepository repository;
    private final PersonagemMapper MAPPER;


    public PersonagemResponse novoPersona(NovoPersonagem dto){
        Personagem novo= MAPPER.toEntity(dto);
        novo = incluirJutsu(dto.jutsus(),novo);
        novo = repository.save(novo);
        return MAPPER.toResponse(novo);
    }


    protected Personagem incluirJutsu(List<Jutsu> jutsus, Personagem personagem) {
        personagem.getJutsus().clear();
        for (Jutsu jutsu : jutsus) {
            Jutsu novoJutsu = new Jutsu();
            novoJutsu.setNome(jutsu.getNome());
            novoJutsu.setPersonagem(personagem);
            personagem.adicionarJutsu(novoJutsu);
        }
        return personagem;
    }


}