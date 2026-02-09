package com.db.desafio.naruto01.service.implementacao;

import com.db.desafio.naruto01.dtos.*;
import com.db.desafio.naruto01.exceptions.ChakraInsuficentesException;
import com.db.desafio.naruto01.exceptions.JutsuNaoEncontradoException;
import com.db.desafio.naruto01.exceptions.PersonagemNaoEncontradoException;
import com.db.desafio.naruto01.exceptions.TipoNaoEncontradoException;
import com.db.desafio.naruto01.mapper.PersonagemMapper;
import com.db.desafio.naruto01.model.*;
import com.db.desafio.naruto01.repository.PersonagemRepository;
import com.db.desafio.naruto01.service.JogoServiceI;
import com.db.desafio.naruto01.service.PersonagemServiceI;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonagemSeviceImpl implements PersonagemServiceI {
    private final PersonagemRepository repository;
    private final PersonagemMapper MAPPER = PersonagemMapper.INSTANCE;

    @Override
    public PersonagemResponse novoPersonagem(NovoPersonagem dto) {
        Personagem novo = criar(dto);
        if (dto.jutsus() != null) {
            dto.jutsus().forEach((nome, dano) ->
                    novo.adicionarJutsu(nome.toLowerCase(), dano)
            );

        }
        Personagem salvo = repository.save(novo);
        return MAPPER.toResponse(salvo);
    }

    @Override
    public void adicionarNovoJutsu(Long id, NovoJutsu novo) {
        Personagem personagem = buscarPersonagem(id);
        personagem.adicionarJutsu(novo.nome(), novo.danoMaximo());
        repository.save(personagem);
    }

    @Override
    public void aumentarChakra(Long id, int chakras) {
        Personagem personagem = buscarPersonagem(id);
        personagem.aumentarChakra(chakras);
        repository.save(personagem);
    }

    @Override
    public void diminuirChakra(Long id, int quantidade){
        Personagem personagem = buscarPersonagem(id);
        if(personagem.getChakra()< quantidade){
            throw new ChakraInsuficentesException("Não há chakras suficientes para essa ação.");
        }
        personagem.diminuirChakra(quantidade);
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



    public Personagem buscarPersonagem(Long id) {
        return repository.findById(id).orElseThrow(() -> new PersonagemNaoEncontradoException(
                "Não há personagem cadastrado no banco com ID:'" + id + "."
        ));
    }

    public void validarJutsuDoPersonagem(Long id, String jutsu){
        Personagem personagem = buscarPersonagem(id);
        validarJutsuDoPersonagem(personagem,jutsu);
    }
    public void validarJutsuDoPersonagem(Personagem personagem, String jutsu){
        boolean possui = personagem.getJutsus()
                .keySet()
                .stream()
                .anyMatch(nome -> nome.equalsIgnoreCase(jutsu));
        if(!possui){
            throw new JutsuNaoEncontradoException("O personagem: '"+personagem.getNome()+
                    "', não possui o jutsu: '"+jutsu+"'.");
        }
    }

    protected Personagem criar(NovoPersonagem dto) {
        Personagem personagem;
        switch (dto.tipoDeNinja()) {
            case NINJUTSU -> personagem = new NinjaDeNinjutsu();
            case TAIJUTSU -> personagem = new NinjaDeTaijutsu();
            case GENJUTSU -> personagem = new NinjaDeGenjutsu();
            default -> throw new TipoNaoEncontradoException("Não é possivel cadastrar " +
                    "um ninja do tipo: '" + dto.tipoDeNinja() + "'.");
        }
        personagem = MAPPER.toUpdate(personagem, dto);
        return personagem;
    }
}