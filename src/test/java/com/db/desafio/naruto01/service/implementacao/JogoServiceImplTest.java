package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.AtacarDeJutsuRequest;
import com.db.desafio.naruto01.dtos.NovoJogoRequest;
import com.db.desafio.naruto01.exceptions.JogadaInvalidaException;
import com.db.desafio.naruto01.exceptions.JogoNaoEncontradoException;
import com.db.desafio.naruto01.fixtures.JogoFixture;
import com.db.desafio.naruto01.model.NinjaDeGenjutsu;
import com.db.desafio.naruto01.model.NinjaDeTaijutsu;
import com.db.desafio.naruto01.model.Personagem;
import com.db.desafio.naruto01.service.PersonagemServiceI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JogoServiceImplTest {

    @InjectMocks
    private JogoServiceImpl service;

    @Mock
    private PersonagemServiceI serviceIPersonagens;


    private Jogo jogo;

    @Spy
    @InjectMocks
    private JogoServiceImpl serviceSpy;


    @Test
    void iniciarJogo() {
        NovoJogoRequest dto = JogoFixture.request();
        Personagem p1 = new NinjaDeGenjutsu();
        Personagem p2 = new NinjaDeTaijutsu();

        when(serviceIPersonagens.buscarPersonagem(1L)).thenReturn(p1);
        when(serviceIPersonagens.buscarPersonagem(2L)).thenReturn(p2);

        service.iniciarJogo(dto);

        verify(serviceIPersonagens).buscarPersonagem(1L);
        verify(serviceIPersonagens).buscarPersonagem(2L);
    }

    @Test
    void usarJutsu() {
        NovoJogoRequest novo = JogoFixture.request();

        Personagem atacante = new NinjaDeTaijutsu();
        atacante.setId(1L);
        atacante.setJutsus(Map.of("rasengan", 50));

        Personagem adversario = new NinjaDeGenjutsu();
        adversario.setId(2L);

        when(serviceIPersonagens.buscarPersonagem(1L)).thenReturn(atacante);
        when(serviceIPersonagens.buscarPersonagem(2L)).thenReturn(adversario);
        when(serviceIPersonagens.usarJutsu(1L)).thenReturn("Ataque realizado");

        service.iniciarJogo(novo);

        AtacarDeJutsuRequest dto = new AtacarDeJutsuRequest(2L, "rasengan");

        String resultado = service.usarJutsu(1L, dto);

        assertEquals("Ataque realizado", resultado);

        verify(serviceIPersonagens).validarJutsuDoPersonagem(1L, "rasengan");
        verify(serviceIPersonagens).diminuirChakra(1L,1);
    }

    @Test
    void deveLancarExcessaoPorJogoNaoInicializado(){
        AtacarDeJutsuRequest dto = new AtacarDeJutsuRequest(2L, "rasengan");

        JogoNaoEncontradoException excption =assertThrows(JogoNaoEncontradoException.class,
                () -> service.usarJutsu(1L, dto));

       assertEquals("Não há jogo inicializado.",excption.getMessage());
    }

    @Test
    void deveDesviarComSucessoQuandoRandomTrue() {
        Long id = 1L;
        NovoJogoRequest dto = JogoFixture.request();
        jogo = new Jogo();
        jogo.ativarDefesa();
        Personagem atacante = JogoFixture.getATACANTE();
        Personagem atacado = JogoFixture.getATACADO();
        String jutsu = "reasegan";
        AtacarDeJutsuRequest atacar= new AtacarDeJutsuRequest(atacado.getId(), jutsu);

        when(serviceIPersonagens.buscarPersonagem(1L)).thenReturn(JogoFixture.getATACANTE());
        when(serviceIPersonagens.buscarPersonagem(2L)).thenReturn(JogoFixture.getATACADO());
        doReturn(true).when(serviceSpy).respostaRandimica();
        when(serviceIPersonagens.desviar(atacado.getId())).thenReturn(atacado.desviar());

        serviceSpy.iniciarJogo(dto);

        serviceSpy.usarJutsu(atacante.getId(),atacar);
        String resposta = serviceSpy.desviar(atacado.getId());

        assertNotNull(resposta);
        assertEquals(atacado.desviar(),resposta);
    }

    @Test
    void naoConseguiuDesviar() {
        Long id = 1L;
        NovoJogoRequest dto = JogoFixture.request();
        jogo = new Jogo();
        jogo.ativarDefesa();
        Personagem atacante = JogoFixture.getATACANTE();
        Personagem atacado = JogoFixture.getATACADO();
        String jutsu = "reasegan";
        AtacarDeJutsuRequest atacar= new AtacarDeJutsuRequest(atacado.getId(), jutsu);

        when(serviceIPersonagens.buscarPersonagem(1L)).thenReturn(JogoFixture.getATACANTE());
        when(serviceIPersonagens.buscarPersonagem(2L)).thenReturn(JogoFixture.getATACADO());
        doReturn(false).when(serviceSpy).respostaRandimica();

        serviceSpy.iniciarJogo(dto);

        serviceSpy.usarJutsu(atacante.getId(),atacar);
        String resposta = serviceSpy.desviar(atacado.getId());

        assertNotNull(resposta);
        assertEquals("Defesa Infrutífera.",resposta);
    }


    @Test
    void deveLancarExcessaoAoValidarDefesaPorFaltaDeAtaque() {
        NovoJogoRequest dto = JogoFixture.request();
        jogo = new Jogo();
        Personagem atacante = JogoFixture.getATACANTE();
        Personagem atacado = JogoFixture.getATACADO();

        when(serviceIPersonagens.buscarPersonagem(1L)).thenReturn(JogoFixture.getATACANTE());
        when(serviceIPersonagens.buscarPersonagem(2L)).thenReturn(JogoFixture.getATACADO());

        service.iniciarJogo(dto);
        JogadaInvalidaException excption=  assertThrows(JogadaInvalidaException.class,
                () -> service.validarDefesa(99L));

        assertEquals("Não há defesa a ser feita.",excption.getMessage());
    }

    @Test
    void deveLancarExcessaoAoValidarDefesaPeloProprioAtacante() {
        NovoJogoRequest dto = JogoFixture.request();
        jogo = new Jogo();
        Personagem atacante = JogoFixture.getATACANTE();
        Personagem atacado = JogoFixture.getATACADO();
        String jutsu = "reasegan";
        AtacarDeJutsuRequest atacar= new AtacarDeJutsuRequest(atacado.getId(), jutsu);

        when(serviceIPersonagens.buscarPersonagem(1L)).thenReturn(JogoFixture.getATACANTE());
        when(serviceIPersonagens.buscarPersonagem(2L)).thenReturn(JogoFixture.getATACADO());

        service.iniciarJogo(dto);
        service.usarJutsu(atacante.getId(), atacar);

        JogadaInvalidaException excption=  assertThrows(JogadaInvalidaException.class,
                () -> service.validarDefesa(atacante.getId()));

        assertEquals("O atacante não pode atacar a si mesmo.",excption.getMessage());
    }
}