package com.db.desafio.naruto01.repository;

import com.db.desafio.naruto01.model.Jutsu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JutsuRepository extends JpaRepository<Jutsu, Long> {

}

