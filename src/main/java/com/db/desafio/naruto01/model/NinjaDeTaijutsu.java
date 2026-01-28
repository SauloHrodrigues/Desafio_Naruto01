package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.interfaces.Ninja;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TAIJUTSU")
public class NinjaDeTaijutsu extends Personagem implements Ninja {

    @Override
    public String usarJutsu() {
        return "O personagem esta usando um golpe de Taijutsu";
    }

    @Override
    public String desviar() {
        return " O personagem está desviando de um ataque usando sua habilidade em Taijutsu. ";
    }

    @Override
    public TipoDeNinja getTipo() {
        return TipoDeNinja.TAIJUTSU;
    }
}
