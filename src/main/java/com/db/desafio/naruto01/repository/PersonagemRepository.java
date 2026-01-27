package com.db.desafio.naruto01.repository;

import com.db.desafio.naruto01.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem,Long> {

    @Query("select p from Personagem p where type(p) = NinjaDeNinjutsu")
    List<Personagem> buscarNinjasDeNinjutsu();

    @Query("select p from Personagem p where type(p) = NinjaDeTaijutsu")
    List<Personagem> buscarNinjasDeTaijutsu();

    @Query("select p from Personagem p where type(p) = NinjaDeGenjutsu")
    List<Personagem> buscarNinjasDeGenjutsu();
}
