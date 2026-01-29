package com.db.desafio.naruto01.fixtures;

import com.db.desafio.naruto01.dtos.NovoJutsu;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.Jutsu;
import com.db.desafio.naruto01.model.Personagem;

public class JutsuFixture {
    private static final Long ID = 5L;
    private static final String NOME = "Rasengan";
    private static final Personagem PERSONAGEM = PersonagemFixture.entity(TipoDeNinja.TAIJUTSU);

   public static NovoJutsu request(){
       return new NovoJutsu(NOME);
   }

   public static Jutsu toEntity(){
       return new Jutsu(ID,NOME,PERSONAGEM);
   }
}
