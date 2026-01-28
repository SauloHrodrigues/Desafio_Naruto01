package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.fixtures.PersonagemFixture;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonagemSeviceImplTest {

    @InjectMocks
    private  PersonagemSeviceImpl service;

    @Mock
    private PersonagemRepository repository;

    @Test
    @DisplayName("Deve cadastrar um novo personangem")
    void cadastrar(){
        NovoPersonagem dto = PersonagemFixture.request(TipoDeNinja.TAIJUTSU);
        Personagem personagem = PersonagemFixture.entity(TipoDeNinja.TAIJUTSU);

        when(repository.save(any(Personagem.class))).thenReturn(personagem);

        PersonagemResponse resposta = service.novoPersonagem(dto);

        assertNotNull(resposta.id());
        assertEquals(resposta.nome(),dto.nome());
        assertEquals(resposta.idade(),dto.idade());
        assertEquals(resposta.chakra(),dto.chakra());
        assertEquals(resposta.jutsus().size(),dto.jutsus().size());
        assertEquals(resposta.tipoDeNinja(),dto.tipoDeNinja());
    }
}