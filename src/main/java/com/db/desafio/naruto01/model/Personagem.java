package com.db.desafio.naruto01.model;

import com.db.desafio.naruto01.interfaces.Ninja;
import com.db.desafio.naruto01.enuns.TipoDeNinja;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_de_ninja")
@Table(name = "personagens")
public abstract class Personagem implements Ninja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String aldeia;
    private int chakra = 100;
    @ElementCollection
    @CollectionTable(
            name = "personagem_jutsus",
            joinColumns = @JoinColumn(name = "personagem_id")
    )
    @MapKeyColumn(name = "nome_jutsu")
    @Column(name = "dano_maximo")
    private Map<String, Integer> jutsus = new HashMap<>();

    public abstract TipoDeNinja getTipo();

    public void adicionarJutsu(String nome, Integer danoMaximo) {
        this.jutsus.put(nome, danoMaximo);
    }

    public void aumentarChakra(int chakra) {
        this.chakra += chakra;
    }

    public void diminuirChakra(int chakra) {
        this.chakra -= chakra;
    }

    @Override
    public String desviar() {
        return "O personagem: " + getNome() + " está desviando de um ataque usando " +
                "sua habilidade em " + getTipo() + ".";
    }

    @Override
    public String usarJutsu() {
        return "O personagem: " + getNome() + " esta usando um golpe de " + getTipo() + ".";
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", Aldeia='" + aldeia + '\'' +
                ", jutsus=" + jutsus +
                ", chakra=" + chakra +
                '}';
    }
}