package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.interfaces.Ninja;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NINJUTSU")
public class NinjaDeNinjutsu extends Personagem implements Ninja {

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
