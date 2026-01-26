package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.enuns.TipoDeNinja;
import com.db.desafio.naruto01.interfaces.Ninja;

import java.util.List;

public class NinjaDeGenjutsu extends Personagem implements Ninja {
    public NinjaDeGenjutsu(Long id, String nome, int idade, String Aldeia, List<String> jutsu, int chakra) {
        super(id, nome, idade, Aldeia, jutsu, chakra, TipoDeNinja.GENJUTSU);
    }

    public NinjaDeGenjutsu() {
        setTipoDeNinja(TipoDeNinja.GENJUTSU);
    }

    @Override
    public String usarJutsu() {
        return "O personagem esta usando um golpe de Gennjutsu";
    }

    @Override
    public String desviar() {
        return " O personagem está desviando de um ataque usando " +
                "sua habilidade em Genjutsu. ";
    }
}
