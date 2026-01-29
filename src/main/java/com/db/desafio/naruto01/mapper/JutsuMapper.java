package com.db.desafio.naruto01.mapper;

import com.db.desafio.naruto01.dtos.JutsuResponse;
import com.db.desafio.naruto01.dtos.NovoJutsu;
import com.db.desafio.naruto01.model.Jutsu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JutsuMapper {
    JutsuMapper INSTANCE = Mappers.getMapper(JutsuMapper.class);

    @Mapping(target = "personagem", ignore = true)
    @Mapping(target = "nome", source = "nomeDoJutsu", qualifiedByName = "nomeMinusculo")
    Jutsu toEntity(NovoJutsu dto);

    @Mapping(target = "personagemId", source = "personagem.id")
    @Mapping(target = "nomeDoJutsu", source = "nome")
    JutsuResponse toResponse(Jutsu jutsu);

    @Named("nomeMinusculo")
    default String nomeMinusculo(String nome) {
        return nome == null ? null : nome.toLowerCase();
    }
}
