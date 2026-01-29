package com.db.desafio.naruto01.mapper;

import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemExibirResponse;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.model.NinjaDeGenjutsu;
import com.db.desafio.naruto01.model.NinjaDeNinjutsu;
import com.db.desafio.naruto01.model.NinjaDeTaijutsu;
import com.db.desafio.naruto01.model.Personagem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = JutsuMapper.class)
public interface PersonagemMapper {

    PersonagemMapper INSTANCE = Mappers.getMapper(PersonagemMapper.class);

    @Mapping(target = "jutsus", ignore = true)
    @Mapping(target = "nome", expression = "java(dto.nome() != null ? dto.nome().toLowerCase() : null)")
    Personagem toUpdate(@MappingTarget Personagem personagem, NovoPersonagem dto);

    @Mapping(source = "tipo", target = "tipoDeNinja")
    PersonagemResponse toResponse(Personagem personagem);


    PersonagemExibirResponse exibirPersonagem(Personagem personagem);

}