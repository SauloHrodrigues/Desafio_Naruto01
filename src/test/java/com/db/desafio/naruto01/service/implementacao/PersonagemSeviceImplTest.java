package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.*;
import com.db.desafio.naruto01.exceptions.PersonagemNaoEncontradoException;
import com.db.desafio.naruto01.fixtures.JutsuFixture;
import com.db.desafio.naruto01.fixtures.PersonagemFixture;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.*;
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

    @Test
    @DisplayName("Deve aumentar a quantidade de chakras de um personangem cadastrado")
    void  deveAumentarQuantidadeDeChakrasDeUmPersonagemCadastrado(){
        Personagem personagem = PersonagemFixture.entity(TipoDeNinja.TAIJUTSU);
        Long idPersonagem = personagem.getId();
        int chakraInicial = personagem.getChakra();
        int chakraAumentar = 100;

        when(repository.findById(idPersonagem)).thenReturn(Optional.of(personagem));

        service.aumentarChakra(idPersonagem,chakraAumentar);

        assertEquals(chakraInicial+chakraAumentar, personagem.getChakra());
        verify(repository).findById(idPersonagem);
        verify(repository).save(personagem);

    }

    @Test
    @DisplayName("Deve retornar mensagem ao atacar de jutsu")
    void deveRetornarMensagemAoAtacarDeJutsu(){
        Personagem personagem = PersonagemFixture.entity(TipoDeNinja.TAIJUTSU);
        Long id= personagem.getId();

        when(repository.findById(id)).thenReturn(Optional.of(personagem));

        String mensagem = service.usarJutsu(id);

        assertEquals("O personagem: "+personagem.getNome()+" esta usando um golpe de "+
                personagem.getTipo()+".",mensagem);
    }

    @Test
    @DisplayName("Deve retornar mensagem ao desviar de um ataque")
    void deveRetornarMensagemAoDesviar(){
        Personagem personagem = PersonagemFixture.entity(TipoDeNinja.TAIJUTSU);
        Long id= personagem.getId();

        when(repository.findById(id)).thenReturn(Optional.of(personagem));

        String mensagem = service.desviar(id);

        assertEquals("O personagem: "+personagem.getNome()+" está desviando de um ataque usando " +
                "sua habilidade em "+personagem.getTipo()+".",mensagem);
    }

    @Test
    @DisplayName("Deve retornar informaçoes do personagem, sem id nem tipo")
    void deveRetornarInformaçoesDoPersonagemSemIdNemTipo(){
        Personagem personagem = PersonagemFixture.entity(TipoDeNinja.NINJUTSU);
        Long id = personagem.getId();

        when(repository.findById(id)).thenReturn(Optional.of(personagem));

        PersonagemExibirResponse resposta = service.exibirPersonagem(id);

        assertEquals(personagem.getNome(),resposta.nome());
        assertEquals(personagem.getIdade(),resposta.idade());
        assertEquals(personagem.getAldeia(),resposta.aldeia());
        assertEquals(personagem.getJutsus().size(),resposta.jutsus().size());
        assertEquals(personagem.getChakra(),resposta.chakra());
    }

    @Test
    @DisplayName("Deve criar personagem de acordo com o tipo de ninja")
    void deveCriarPersonagemDeAcordoComOTipoDeNinja(){
        NovoPersonagem genjutsu = PersonagemFixture.request(TipoDeNinja.GENJUTSU);
        NovoPersonagem ninjutsu = PersonagemFixture.request(TipoDeNinja.NINJUTSU);
        NovoPersonagem taijutsu = PersonagemFixture.request(TipoDeNinja.TAIJUTSU);

        Personagem respostaGenjutsu = service.criar(genjutsu);
        Personagem respostaNinjutsu = service.criar(ninjutsu);
        Personagem respostaTaijutsu = service.criar(taijutsu);

        assertInstanceOf(NinjaDeGenjutsu.class, respostaGenjutsu);
        assertInstanceOf(NinjaDeNinjutsu.class, respostaNinjutsu);
        assertInstanceOf(NinjaDeTaijutsu.class, respostaTaijutsu);
    }
}