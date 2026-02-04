package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.enuns.TipoDeNinja;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NINJUTSU")
public class NinjaDeNinjutsu extends Personagem {

    @Override
    public String usarJutsu() {
        return "O personagem: " + getNome() + " esta usando um golpe de " + getTipo() + ".";
    }

    @Override
    public String desviar() {
        return "O personagem: " + getNome() + " está desviando de um ataque usando " +
                "sua habilidade em " + getTipo() + ".";
    }

    @Override
    public TipoDeNinja getTipo() {
        return TipoDeNinja.NINJUTSU;
    }
}