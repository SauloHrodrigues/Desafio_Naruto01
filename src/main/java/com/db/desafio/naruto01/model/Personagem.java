package com.db.desafio.naruto01.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_de_ninja")
@Table(name = "personagens")
public abstract class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String Aldeia;
    @OneToMany(
            mappedBy = "personagem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Jutsu> jutsus = new ArrayList<>();
    private int chakra;

    public void adicionarJutsu(Jutsu jutsu){
        jutsus.add(jutsu);
        jutsu.setPersonagem(this);

    }

    public void adicionarJutsu(List<Jutsu> jutsus){
        this.jutsus.addAll(jutsus);
        jutsus.forEach(this::adicionarJutsu);
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