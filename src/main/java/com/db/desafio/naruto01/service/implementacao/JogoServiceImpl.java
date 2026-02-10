package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.AtacarDeJutsuRequest;
import com.db.desafio.naruto01.dtos.NovoJogoRequest;
import com.db.desafio.naruto01.exceptions.JogadaInvalidaException;
import com.db.desafio.naruto01.exceptions.JogoNaoEncontradoException;
import com.db.desafio.naruto01.model.Personagem;
import com.db.desafio.naruto01.service.PersonagemServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class JogoServiceImpl {

    private final PersonagemServiceI serviceIPersonagens;
    private Jogo jogo;

    public void iniciarJogo(NovoJogoRequest dto) {
        jogo = new Jogo();
        for(Long id: dto.ids()){
            Personagem personagem = serviceIPersonagens.buscarPersonagem(id);
            jogo.adicionarJogador(personagem);
        }
    }

    public String usarJutsu(Long id, AtacarDeJutsuRequest dto){
        validaJogo();
        jogo.setAtacante(id);
        jogo.validaJogador(dto.idAdversario());
        serviceIPersonagens.validarJutsuDoPersonagem(id, dto.jutsu());
        serviceIPersonagens.diminuirChakra(id,1);
        Personagem personagem = serviceIPersonagens.buscarPersonagem(id);
        int danoMaximo =(int) personagem.getJutsus().get(dto.jutsu());
        jogo.gerarAtaque(id,danoMaximo,5);
        jogo.ativarDefesa();
        return serviceIPersonagens.usarJutsu(id);
    }

    public String desviar(Long id){
        validarDefesa(id);
        if(respostaRandimica()){
            jogo.consumirDefesa();
            return serviceIPersonagens.desviar(id);
        } else {
             serviceIPersonagens.diminuirChakra(id,10);
            jogo.consumirDefesa();
             return "Defesa Infrutífera.";
        }
    }

    protected void validarDefesa(Long id){
        if(!jogo.isDefesaAtiva()){
            throw new JogadaInvalidaException("Não há defesa a ser feita.");
        }
        if (Objects.equals(id, jogo.getAtacante())) {
            throw new JogadaInvalidaException("O atacante não pode atacar a si mesmo.");
        }
    }

    protected boolean respostaRandimica() {
        Random random = new Random();
        return random.nextBoolean();
    }

    protected void validaJogo(){
        if(jogo == null){
            throw new JogoNaoEncontradoException("Não há jogo inicializado.");
        }
    }
}