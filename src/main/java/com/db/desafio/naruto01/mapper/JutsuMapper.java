package com.db.desafio.naruto01.mapper;

import com.db.desafio.naruto01.dtos.JutsuResponse;
import com.db.desafio.naruto01.dtos.NovoJutsu;
import com.db.desafio.naruto01.model.Jutsu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JutsuMapper {
    JutsuMapper INSTANCE = Mappers.getMapper(JutsuMapper.class);

    @Mapping(target = "personagem", ignore = true)
    Jutsu toEntity(NovoJutsu dto);

    @Mapping(target = "personagemId", source = "personagem.id")
    JutsuResponse toResponse(Jutsu jutsu);
}
