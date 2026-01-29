package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.JutsuResponse;
import com.db.desafio.naruto01.dtos.NovoJutsu;
import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.exceptions.PersonagemNaoEncontradoException;
import com.db.desafio.naruto01.fixtures.JutsuFixture;
import com.db.desafio.naruto01.fixtures.PersonagemFixture;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.Jutsu;
import com.db.desafio.naruto01.model.Personagem;
import com.db.desafio.naruto01.repository.PersonagemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonagemSeviceImplTest {

    @InjectMocks
    private  PersonagemSeviceImpl service;

    @Mock
    private PersonagemRepository repository;

    @Test
    @DisplayName("Deve cadastrar um novo personangem")
    void deveCadastrarUmPersonagemComSucesso(){
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

    @Test
    @DisplayName("Deve adicionar um novo jutsu a um personangem já cadastrado")
    void deveAdicionarUmNovoJutsu(){
        Personagem personagem = PersonagemFixture.entity(TipoDeNinja.TAIJUTSU);
        NovoJutsu request = JutsuFixture.request();
        Jutsu jutsu = JutsuFixture.toEntity();
        Long idPersonagem = personagem.getId();

        when(repository.findById(idPersonagem)).thenReturn(Optional.of(personagem));
        when(repository.save(any(Personagem.class)))
                .thenAnswer(invocation -> {
                    Personagem p = invocation.getArgument(0);

                    long idGerado = 1L;
                    for (Jutsu j : p.getJutsus()) {
                        j.setId(idGerado++);
                        j.setPersonagem(p); // garante o lado dono
                    }

                    return p;
                });

        JutsuResponse resposta = service.adicionarNovoJutsu(idPersonagem,request);

        assertNotNull(resposta);
        assertNotNull(resposta.id());
        assertEquals(request.nomeDoJutsu().toLowerCase(), resposta.nomeDoJutsu());
        assertEquals(1L, resposta.personagemId());


    }

    @Test
    @DisplayName("Deve lançar excessão de personangem não cadastrado")
    void deveLancarExcessaoDePersonagemNaoCadastrado(){
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        PersonagemNaoEncontradoException exception = assertThrows(PersonagemNaoEncontradoException.class,()->{
            service.buscarPersonagem(id);
        });

        assertEquals("Não há personagem cadastrado no banco com ID:'" + id + ".",
                exception.getMessage());
        verify(repository).findById(id);
    }
}