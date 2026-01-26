package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.enuns.TipoDeNinja;
import com.db.desafio.naruto01.interfaces.Ninja;

import java.util.List;

public class NinjaDeNinjutsu extends Personagem implements Ninja {
    public NinjaDeNinjutsu(Long id, String nome, int idade, String Aldeia, List<String> jutsu, int chakra) {
        super(id, nome, idade, Aldeia, jutsu, chakra, TipoDeNinja.NINJUTSU);
    }

    public NinjaDeNinjutsu() {
        setTipoDeNinja(TipoDeNinja.NINJUTSU);
    }

    @Override
    public String usarJutsu() {
        return "O personagem esta usando um golpe de Ninjutsu";
    }

    @Override
    public String desviar() {
        return " O personagem está desviando de um ataque usando " +
                "sua habilidade em Ninjutsu. ";
    }
}
