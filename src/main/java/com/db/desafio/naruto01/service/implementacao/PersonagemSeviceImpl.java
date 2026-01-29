package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.*;
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
        Jutsu jutsuSalvo = personagem.getJutsus().getLast();
        return MAPPER_JUTSU.toResponse(jutsuSalvo);
    }

    public void aumentarChakra(Long id, int chakras) {
        Personagem personagem = buscarPersonagem(id);
        personagem.aumentarChakra(chakras);
        repository.save(personagem);
    }

    @Override
    public String usarJutsu(Long id) {
        Personagem personagem = buscarPersonagem(id);
        return personagem.usarJutsu();
    }

    @Override
    public String desviar(Long id) {
        Personagem personagem = buscarPersonagem(id);
        return personagem.desviar();
    }

    @Override
    public PersonagemExibirResponse exibirPersonagem(Long id) {
        Personagem personagem = buscarPersonagem(id);
        return MAPPER.exibirPersonagem(personagem);
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