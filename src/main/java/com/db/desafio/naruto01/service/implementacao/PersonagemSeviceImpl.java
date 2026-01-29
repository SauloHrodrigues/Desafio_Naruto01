package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.JutsuResponse;
import com.db.desafio.naruto01.dtos.NovoJutsu;
import com.db.desafio.naruto01.dtos.NovoPersonagem;
import com.db.desafio.naruto01.dtos.PersonagemResponse;
import com.db.desafio.naruto01.exceptions.PersonagemNaoEncontradoException;
import com.db.desafio.naruto01.exceptions.TipoNaoEncontradoException;
import com.db.desafio.naruto01.mapper.JutsuMapper;
import com.db.desafio.naruto01.mapper.PersonagemMapper;
import com.db.desafio.naruto01.model.*;
import com.db.desafio.naruto01.repository.PersonagemRepository;
import com.db.desafio.naruto01.service.PersonagemServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonagemSeviceImpl implements PersonagemServiceI {
    private final PersonagemRepository repository;
    private final PersonagemMapper MAPPER = PersonagemMapper.INSTANCE;
    private static final JutsuMapper MAPPER_JUTSU = JutsuMapper.INSTANCE;


    @Override
    public PersonagemResponse novoPersonagem(NovoPersonagem dto) {
        Personagem novo = criar(dto);

        for (NovoJutsu novoJutsu : dto.jutsus()) {
            Jutsu jutsu = MAPPER_JUTSU.toEntity(novoJutsu);
            novo.adicionarJutsu(jutsu);
        }

        Personagem salvo = repository.save(novo);

        return MAPPER.toResponse(salvo);
    }

    @Override
    public JutsuResponse adicionarNovoJutsu(Long id, NovoJutsu novo) {
        Personagem personagem = buscarPersonagem(id);
        Jutsu jutsu = MAPPER_JUTSU.toEntity(novo);
        personagem.adicionarJutsu(jutsu);
        personagem = repository.save(personagem);
        Jutsu jutsuSalvo = personagem.getJutsus().get(
                personagem.getJutsus().size()-1);
        return MAPPER_JUTSU.toResponse(jutsuSalvo);
    }

    protected Personagem buscarPersonagem(Long id) {
        return repository.findById(id).orElseThrow(() -> new PersonagemNaoEncontradoException(
                "Não há personagem cadastrado no banco com ID:'" + id + "."
        ));
    }


    protected Personagem criar(NovoPersonagem dto) {
        Personagem personagem;
        switch (dto.tipoDeNinja()) {
            case NINJUTSU -> personagem = new NinjaDeNinjutsu();
            case TAIJUTSU -> personagem = new NinjaDeTaijutsu();
            case GENJUTSU -> personagem = new NinjaDeGenjutsu();
            default -> throw new TipoNaoEncontradoException("Não é possivel cadastrar " +
                    "um ninja do tipo: '" + dto.tipoDeNinja()+"'.");
        }
        personagem = MAPPER.toUpdate(personagem,dto);
        return personagem;
    }
}