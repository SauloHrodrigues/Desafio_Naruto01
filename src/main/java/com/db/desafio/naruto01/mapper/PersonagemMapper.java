package com.db.desafio.naruto01.mapper;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import com.db.desafio.naruto01.model.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class PersonagemMapper {
    @ObjectFactory
    protected Personagem criar(NovoPersonagem dto) {
        return switch (dto.tipoDeNinja()) {
            case NINJUTSU -> new NinjaDeNinjutsu();
            case TAIJUTSU -> new NinjaDeTaijutsu();
            case GENJUTSU -> new NinjaDeGenjutsu();
        };
    }
    @Mapping(target = "jutsus", ignore = true)
    public abstract Personagem toEntity(NovoPersonagem dto);

    @Mapping(target = "tipoDeNinja", expression = "java(extrairTipo(personagem))")
    public abstract PersonagemResponse toResponse(Personagem personagem);

    protected TipoDeNinja extrairTipo(Personagem personagem) {
        if (personagem instanceof NinjaDeNinjutsu) return TipoDeNinja.NINJUTSU;
        if (personagem instanceof NinjaDeTaijutsu) return TipoDeNinja.TAIJUTSU;
        if (personagem instanceof NinjaDeGenjutsu) return TipoDeNinja.GENJUTSU;
        throw new IllegalStateException("Tipo de ninja desconhecido");
    }

}
