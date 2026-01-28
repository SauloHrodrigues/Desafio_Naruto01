package com.db.desafio.naruto01.dtos;

import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.Jutsu;

import java.util.List;

public record NovoPersonagem(
        String nome,
        int idade,
        String aldeia,
        List<NovoJutsu> jutsus,
        TipoDeNinja tipoDeNinja,
        int chakra
) {}
