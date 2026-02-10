package com.db.desafio.naruto01.fixtures;

import com.db.desafio.naruto01.dtos.NovoJogoRequest;
import com.db.desafio.naruto01.enuns.TipoDeNinja;
import com.db.desafio.naruto01.model.Personagem;
import com.db.desafio.naruto01.service.implementacao.Jogo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class JogoFixture {
    @Getter
    private static final Personagem ATACANTE = PersonagemFixture.entity(1L, TipoDeNinja.GENJUTSU,"reasegan",100);
   @Getter
    private static final Personagem ATACADO = PersonagemFixture.entity(2L, TipoDeNinja.TAIJUTSU,"Kage Bunshin",100);

    public static NovoJogoRequest request(){
        List<Long>ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        return new NovoJogoRequest(ids);
    }

    public static Jogo entity(){
        Jogo jogo=  new Jogo();
        jogo.setAtacante(ATACANTE.getId());
        jogo.setAtacante(ATACADO.getId());
        return jogo;
    }
}
