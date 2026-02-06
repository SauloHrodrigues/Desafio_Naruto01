package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.enuns.TipoDeNinja;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TAIJUTSU")
public class NinjaDeTaijutsu extends Personagem {

    @Override
    public TipoDeNinja getTipo() {
        return TipoDeNinja.TAIJUTSU;
    }
}