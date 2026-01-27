package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.fixtures.PersonagemFixture;
import com.db.desafio.naruto01.mapper.PersonagemMapper;
import com.db.desafio.naruto01.model.Personagem;
import com.db.desafio.naruto01.repository.PersonagemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonagemSeviceImplTest {

    @InjectMocks
    private  PersonagemSeviceImpl service;

    @Mock
    private PersonagemRepository repository;

    @Mock
    private PersonagemMapper mapper;

    @Test
    @DisplayName("Deve cadastrar um novo personangem")
    void cadastrar(){
        NovoPersonagem dto = PersonagemFixture.requestGenjutsu();
        Personagem personagem= PersonagemFixture.toEntity(dto);
        Personagem personagemComId= PersonagemFixture.toEntityComID(dto);
        PersonagemResponse response = PersonagemFixture.toResponse(dto);

        when(mapper.toEntity(dto)).thenReturn(personagem);
        when(repository.save(personagem)).thenReturn(personagemComId);
        when(mapper.toResponse(personagemComId)).thenReturn(response);

        PersonagemResponse resposta = service.novoPersona(dto);

        assertNotNull(resposta.id());
        assertEquals(dto.nome(),response.nome());
        assertEquals(dto.idade(),response.idade());
        assertEquals(dto.chakra(),response.chakra());
        assertEquals(dto.jutsus().size(),response.jutsus().size());
        assertEquals(dto.tipoDeNinja(),response.tipoDeNinja());
    }
}