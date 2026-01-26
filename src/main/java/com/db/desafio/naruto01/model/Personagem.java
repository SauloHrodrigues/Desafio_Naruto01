package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.enuns.TipoDeNinja;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "personagens")
public abstract class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String Aldeia;
    private List<String> jutsus = new ArrayList<>();
    private int chakra;
    private TipoDeNinja tipoDeNinja;

    public void adicionarJutsu(String jutsu){
        jutsus.add(jutsu.toLowerCase(Locale.ROOT));
    }

    public void aumentarChakra(int chakra){
        this.chakra += chakra;
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", Aldeia='" + Aldeia + '\'' +
                ", jutsus=" + jutsus +
                ", chakra=" + chakra +
                '}';
    }
}