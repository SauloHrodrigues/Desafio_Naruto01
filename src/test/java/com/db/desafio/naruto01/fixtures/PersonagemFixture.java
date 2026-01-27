package com.db.desafio.naruto01.fixtures;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.*;

import java.util.ArrayList;
import java.util.List;

public class PersonagemFixture {
    private static final Long ID = 1L;
    private static final String NOME = "nome";
    private static final int IDADE = 18;
    private static final String ALDEIA = "aldeia";
    private static final int CHAKRA = 25;
    private static final List<Jutsu> JUTSU_LIST = new ArrayList<>(List.of(RASEGAN(), KAGE_BUNSIN()));

    private static Jutsu RASEGAN() {
        return new Jutsu(2L, "Rasegan", null);
    }

    private static Jutsu KAGE_BUNSIN () {
        return new Jutsu(3L,"Kage Bunshin",null);
    }

    public static NovoPersonagem requestGenjutsu(){
        NovoPersonagem novoPersonagem = new NovoPersonagem(
                NOME,IDADE,ALDEIA,JUTSU_LIST,TipoDeNinja.GENJUTSU,CHAKRA);
        return novoPersonagem;
    }

    public static NovoPersonagem requestTaijutsu(){
        NovoPersonagem novoPersonagem = new NovoPersonagem(
                NOME,IDADE,ALDEIA, JUTSU_LIST,TipoDeNinja.TAIJUTSU,CHAKRA);
        return novoPersonagem;
    }

    public static NovoPersonagem requestNinjutsu(){
        NovoPersonagem novoPersonagem = new NovoPersonagem(
                NOME,
                IDADE,
                ALDEIA,
                JUTSU_LIST,
                TipoDeNinja.NINJUTSU,CHAKRA);
        return novoPersonagem;
    }

    public static Personagem toEntityComID(NovoPersonagem dto){
        Personagem personagem = toEntity(dto);
        personagem.setId(ID);
        return personagem;
    }
    public static Personagem toEntity(NovoPersonagem dto){
        Personagem personagem = switch (dto.tipoDeNinja()){
            case NINJUTSU -> new NinjaDeNinjutsu();
            case TAIJUTSU -> new NinjaDeTaijutsu();
            case GENJUTSU -> new NinjaDeGenjutsu();
        };
        personagem.setNome(dto.nome());
        personagem.setIdade(dto.idade());
        personagem.setAldeia(dto.aldeia());
        personagem.setJutsus(dto.jutsus());
        personagem.setChakra(dto.chakra());
        return personagem;
    }

    public static PersonagemResponse toResponse(NovoPersonagem dto){
        return new PersonagemResponse(
                ID,
                dto.nome(),
                dto.idade(),
                dto.aldeia(),
                dto.jutsus(),
                dto.tipoDeNinja(),
                dto.chakra()
        );
    }
}
