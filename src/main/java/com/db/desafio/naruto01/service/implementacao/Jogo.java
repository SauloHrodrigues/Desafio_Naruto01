package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.exceptions.PersonagemNaoEncontradoException;
import com.db.desafio.naruto01.model.Jutsu;
import com.db.desafio.naruto01.model.Personagem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
public class Jogo {
    private Set<Long> idsJogadores = new HashSet<>();
   @Getter
    private Map<Long,Jutsu> ataque;

    @Getter
    private Long atacante;
    @Getter
    private boolean defesaAtiva = false;

    public void adicionarJogador(Personagem personagem){
        idsJogadores.add(personagem.getId());
    }

    public void setAtacante(Long atacante) {
        validaJogador(atacante);
        this.atacante = atacante;
    }

    public void gerarAtaque(Long id,int dano, int consumoDeChakras) {
        validaJogador(id);
        ataque = new HashMap<>();
        ataque.put(id, new Jutsu(dano, consumoDeChakras));
    }

    public void validaJogador(Long id){
        if (!idsJogadores.contains(id)) {
            throw new PersonagemNaoEncontradoException("Personagem com id: " + id +
                    " não esta no jogo.");
        }
    }

    public void ativarDefesa(){
        defesaAtiva = true;
    }
    public void consumirDefesa(){
        defesaAtiva = false;
    }

}