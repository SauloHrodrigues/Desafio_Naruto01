package com.db.desafio.naruto01.integrados;

import com.db.desafio.naruto01.dtos.*;
import com.db.desafio.naruto01.fixtures.JutsuFixture;
import com.db.desafio.naruto01.fixtures.PersonagemFixture;
import com.db.desafio.naruto01.interfaces.TipoDeNinja;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonagemITest {
    @Autowired
    private TestRestTemplate template;

    @Test//1
    @DisplayName("Deve cadastrar um novo personagem!")
    public void deveCadastrarUmNovoPersonagemComSucesso() {
        NovoPersonagem dto = PersonagemFixture.request(TipoDeNinja.GENJUTSU);
        ResponseEntity<PersonagemResponse> resposta = template.postForEntity(
                "/personagens", dto, PersonagemResponse.class);
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertNotNull(resposta.getBody());
        assertThat(resposta.getBody().id()).isNotNull();
        assertThat(resposta.getBody().nome()).isEqualTo(dto.nome().toLowerCase());
        assertThat(resposta.getBody().idade()).isEqualTo(dto.idade());
        assertThat(resposta.getBody().aldeia()).isEqualTo(dto.aldeia());
        assertThat(resposta.getBody().chakra()).isEqualTo(dto.chakra());
        assertThat(resposta.getBody().jutsus()).isNotNull();
        assertThat(resposta.getBody().tipoDeNinja()).isEqualTo(dto.tipoDeNinja());
    }

    @Test//2
    @DisplayName("Deve adicionar um novo jutsu a um personangem já cadastrado")
    @Sql(scripts = {"/gerar_banco.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deveAdicionarUmNovoJutsu() {
        Long id = 1L;
        NovoJutsu dto = JutsuFixture.request();
        ResponseEntity<JutsuResponse> resposta = template.postForEntity(
                "/personagens/{id}/jutsus", dto, JutsuResponse.class, id);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(resposta.getBody());
        assertThat(resposta.getBody().personagemId()).isEqualTo(id);
        assertThat(resposta.getBody().nomeDoJutsu()).isEqualTo(dto.nomeDoJutsu().toLowerCase());
        assertThat(resposta.getBody().personagemId()).isEqualTo(id);
    }

    @Test//3
    @DisplayName("Deve aumentar a quantidade de chakras de um personangem cadastrado")
    @Sql(scripts = {"/gerar_banco.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deveAumentarQuantidadeDeChakrasDeUmPersonagemCadastrado() {
        Long id = 1L;
        int chakras = 100;

        ResponseEntity<Void> resposta = template.exchange(
                "/personagens/{id}/chakras?chakras={chakras}",
                HttpMethod.PUT,
                HttpEntity.EMPTY,
                Void.class,
                id,
                chakras
        );
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test//4
    @DisplayName("Deve retornar mensagem ao atacar de jutsu")
    @Sql(scripts = {"/gerar_banco.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deveRetornarMensagemAoAtacarDeJutsu() {
        Long id = 1L;
        ResponseEntity<String> resposta =
                template.exchange(
                        "/personagens/{id}/atacar",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<String>() {
                        }, id
                );

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isEqualTo("O personagem: Rock Lee esta usando um golpe de TAIJUTSU.");

    }

    @Test//5
    @DisplayName("Deve retornar mensagem ao realizar defesa")
    @Sql(scripts = {"/gerar_banco.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deveRetornarMensagemAoRealizarDefesa() {
        Long id = 1L;
        ResponseEntity<String> resposta =
                template.exchange(
                        "/personagens/{id}/desviar",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<String>() {
                        }, id
                );

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isEqualTo(
                "O personagem: Rock Lee está desviando de um ataque usando sua habilidade em TAIJUTSU.");
    }

    @Test//6
    @DisplayName("Deve retornar mensagem ao realizar defesa")
    @Sql(scripts = {"/gerar_banco.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deveRetornarDadosEspecificosDeUmPersonagem() {
        Long id = 1L;
        ResponseEntity<PersonagemExibirResponse> resposta =
                template.exchange(
                        "/personagens/{id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<PersonagemExibirResponse>() {
                        }, id
                );
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isNotNull();
        assertThat(resposta.getBody().nome()).isNotNull();
        assertThat(resposta.getBody().idade()).isNotNull();
        assertThat(resposta.getBody().aldeia()).isNotNull();
        assertThat(resposta.getBody().chakra()).isNotNull();
        assertThat(resposta.getBody().jutsus().size()).isEqualTo(2);
    }
}