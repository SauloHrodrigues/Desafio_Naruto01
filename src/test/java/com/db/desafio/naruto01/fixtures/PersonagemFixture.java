package com.db.desafio.naruto01.fixtures;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.enuns.TipoDeNinja;
import com.db.desafio.naruto01.model.NinjaDeGenjutsu;
import com.db.desafio.naruto01.model.NinjaDeNinjutsu;
import com.db.desafio.naruto01.model.NinjaDeTaijutsu;
import com.db.desafio.naruto01.model.Personagem;

import java.util.HashMap;
import java.util.Map;

public class PersonagemFixture {
    private static final Long ID = 1L;
    private static final String NOME = "Nome";
    private static final int IDADE = 18;
    private static final String ALDEIA = "aldeia";
    private static final int CHAKRA = 25;
    private static final int DANO_MAXIMO = 25;
    private static final String JUTSU = "rasengan";


    public static NovoPersonagem request(TipoDeNinja tipo) {
        Map<String,Integer> jutsus = new HashMap<>();
        jutsus.put(JUTSU,DANO_MAXIMO);
        return new NovoPersonagem(
                NOME, IDADE, ALDEIA, jutsus, tipo, CHAKRA);
    }

    public static Personagem entity(TipoDeNinja tipo) {
        Map<String,Integer> jutsus = new HashMap<>();
        jutsus.put(JUTSU.toLowerCase(),DANO_MAXIMO);
        Personagem personagem = TIPO_NINJA(tipo);
        personagem.setId(ID);
        personagem.setNome(NOME);
        personagem.setIdade(IDADE);
        personagem.setAldeia(ALDEIA);
        personagem.adicionarJutsu(JUTSU,DANO_MAXIMO);
        personagem.setChakra(CHAKRA);
        return personagem;
    }

    public static Personagem entity(Long id,TipoDeNinja tipo,String jutsu, int danoMaximo) {
        Map<String,Integer> jutsus = new HashMap<>();
        jutsus.put(jutsu.toLowerCase(),danoMaximo);
        Personagem personagem = TIPO_NINJA(tipo);
        personagem.setId(id);
        personagem.setNome(NOME);
        personagem.setIdade(IDADE);
        personagem.setAldeia(ALDEIA);
        personagem.adicionarJutsu(jutsu.toLowerCase(),danoMaximo);
        personagem.setChakra(CHAKRA);
        return personagem;
    }

    private static Personagem TIPO_NINJA(TipoDeNinja tipoDeNinja) {
        return switch (tipoDeNinja) {
            case NINJUTSU -> new NinjaDeNinjutsu();
            case TAIJUTSU -> new NinjaDeTaijutsu();
            case GENJUTSU -> new NinjaDeGenjutsu();
        };
    }
}