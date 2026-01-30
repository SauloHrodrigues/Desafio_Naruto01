# Desafio_Naruto01

A API foi desenvolvida para gerenciar o jogo Naruto. Seu contexto de desenvolvimento é o exercício e desafio
de boas práticas de programação orientada a objeto, bem como a aplicação das arquiteturas de mercado.Boas práticas 
utilizando padrões de projeto, princípios SOLID e conceitos de Clean Code, além do desenvolvimento de testes unitários
e automatizados utilizando JUnit e Mockito, bem como testes de integração. A API é uma segunda versão, visando a evolução
do código. O fator tempo também foi fator de avaliação. Para tanto, o presente projeto teve como prazo 5 dias.

---

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/Docker_Compose-384d54?style=for-the-badge&logo=docker&logoColor=white)
![MapStruct](https://img.shields.io/badge/MapStruct-FF6C37?style=for-the-badge&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-DB7093?style=for-the-badge&logo=mockito&logoColor=white)
![Testes Unitários](https://img.shields.io/badge/Testes_Unitários-6E5494?style=for-the-badge&logo=testrail&logoColor=white)
![Testes Integrados](https://img.shields.io/badge/Testes_Integrados-0078D6?style=for-the-badge&logo=selenium&logoColor=white)
![H2 Database](https://img.shields.io/badge/Database-H2-1E88E5?style=for-the-badge)

---

## Pré-requisitos

- Docker instalado ([link](https://docs.docker.com/get-docker/))
- Docker Compose instalado (normalmente já vem com Docker Desktop)

---
## Sobre o Projeto


Neste desafio, vamos praticar nossas habilidades em programação orientada a objetos
utilizando o anime Naruto como exemplo. Para isso, vamos criar classes em Java que
representem os personagens do anime Naruto e utilizá-las para mostrar como herança
e interface podem ser usadas para organizar classes relacionadas.


O módulo **Perssonagem** possui os seguintes atributos:
```
• id (Long)
• Nome (String) 
• Idade (int) 
• Aldeia (String) 
• Jutsus (array de Strings) 
• Chakra (int)
```

O **id** é a chave primária que faz uso da estrategia **GenerationType.IDENTITY**, para persistir no banco de dados.
O **Personagem** é classe abstrata que e estendida para as Classes:

```
* NinjaDeGenjutsu;
* NinjaDeTaiJutsu;
* NinjaDeNinJutsu;
```
## Do Banco de Dados

O projeto faz uso do **postgres**, que deve se instalado em um container no docker;

---

## Configuração do ambiente


O `docker-compose.yml` está na pasta docker na raiz do projeto, e configurado
para usar as variáveis contidas no arquivo **.env**, conforme modelo a seguir:

Na raiz do projeto deve-se criar um arqivo .env, que deverá conter as seguites variáveis de ambiente:

````
POSTGRES_USER=teste123
POSTGRES_PASSWORD=teste123
POSTGRES_DB=db_biblioteca
DB_URL=jdbc:postgresql://postgres:5432/db_naruto
```` 

---

## Executando o projeto com Docker

No terminal, na raiz do projeto, execute:

```bash
docker-compose up --build
```

Isso vai:

- Construir a imagem da aplicação
- Subir os containers da aplicação e do banco PostgreSQL
- Configurar o banco com as variáveis do `.env`

A aplicação ficará disponível em: [http://localhost:8080](http://localhost:8080)

---

## Parando a execução

Para parar e remover os containers, execute:

```bash
docker-compose down
```
---

## Rodando localmente (sem Docker)

Se preferir rodar localmente, configure seu banco PostgreSQL e altere
o arquivo  `application.yaml` do Spring Boot com as credenciais da sua maquina local.

---

## Funcionalidades

### Endpoints

#### Endpoint **criar** um novo Personagem:

```
   <<POST>> http://localhost:8080/personagens
```
Para que seja feito o cadastro, deve-se encaminhar no body da requisição um JSON 
com a seguinte estrutura:

```
    {
    "nome": "Maria",
    "idade": 22,
    "aldeia": "aldeia 01",
    "jutsus": [
        {
            "nome": "Rasengan"
        },
        {
            "nome": "Kage Bunshin"
        }
    ],
    "tipoDeNinja": "NINJUTSU",
    "chakra": 25
}
```
A API deverá retornar a seguinte resposta:

```json
    {
  "id": 5,
  "nome": "miria",
  "idade": 22,
  "aldeia": "aldeia 01",
  "tipoDeNinja": "NINJUTSU",
  "chakra": 25,
  "jutsus": [
    {
      "id": 6,
      "nomeDoJutsu": "rasengan",
      "personagemId": 5
    },
    {
      "id": 7,
      "nomeDoJutsu": "kage bunshin",
      "personagemId": 5
    }
  ]
}
```
Endpoint para **adicionar um jutsu** a um personagem já existente no banco: 

```
   <<POST>> http://localhost:8080/personagens/{id}/jutsus
```
Informando o id do personagem na URL, também deve ser enviado, no corpo da requisição o seguinte JSON:

```json
    {
        "nomeDoJutsu": "rasengan"
    }
```

Contando com o seguinte retorno:

````json
    {
          "id": 8,
          "nomeDoJutsu": "rasengan",
          "personagemId": 1
    }
````

Endpoit para **adicionar chakra**.

```
   <<PUT>> http://localhost:8080/personagens/{id}/chakras?chakras={quantidade de chakras}
```
Para adicionar chakras, deve-se informar na URL o id do personagem, bem como a quantidade de chakras a serem
adicionados.

Esse endpoint não tráz nenhum arquivo de resposta, excerto o statusCode **HttpStatus.NO_CONTENT** para o 
sucesso da operação.

Endpoit para **usar jutsu**.

```
    <<GET>> http://localhost:8080/personagens/{id}/atacar
```
Usar jutsu representa um ataque, mas por ora deve devover uma String cm o seguinte conteúdo:

```
    "O personagem: "{nome do personagem}" esta usando um golpe de "{tipo de ninja}".";
```

Endpoit para **desviar**.

````
   <<GET>> http://localhost:8080/personagens/{id}/desviar
````

Assim como usar jutsu, desviar seria a defesa do ninja, no entanto, po ora, o retorno deve ser 
uma String, no seguinte formato:

````
       "O personagem: "{Nome do personagem}" está desviando de um ataque usando " +
                "sua habilidade em "{tipo de ninja}()+".";
````

Endpoit **Mostrar Personagem**.

```
    <<GET>> http://localhost:8080/personagens/{id}
```
O endpoint mostrar personagem foi desenvolvido para, pelo id, a API busca os dados no banco e 
retorna o seguinte JSON:

````json
      {
  "nome": "Saulo",
  "idade": 22,
  "aldeia": "aldeia 01",
  "chakra": 25,
  "jutsus": [
    {
      "id": 8,
      "nomeDoJutsu": "rasengan",
      "personagemId": 1
    }
  ]
}
````
---

## Testes

Realizei testes unitários utilizando JUnit e Mockito. Criei classes Fixitures para reutilização de código nos testes.
Também foi desemvolvido os testes de indegração, com o objetivo de minimizar o máximo de erros. Vale dizer, que para a realização 
dos testes integrados, utilizamos o banco de dados em memória **H2**, bem como instruções sql, formando tabelas e registros 
para a realização dos teste de integração;

A execução dos testes pode ser realizadas pelo terminal, na pasta do projeto, seguindo os seguintes passos:

Para maquinas com o maven instalado, usa-se o seguinte comando:

```Bash
       mvn test
```
Para maquinas sem maven, execute o seguinte comando:

```Bash
    ./mvnw test
  
```
---

## Estrutura do projeto

- `src/main/java`: Código fonte da aplicação
- `src/main/resources`: Configurações e arquivos estáticos
- `src/test/resources`: Configurações e arquivos estáticos de testes
- `docker-compose.yml`: Configuração do Docker Compose
- `.env`: Variáveis de ambiente (não versionar se conter dados sensíveis)
- `Dockerfile`: Dockerfile para a aplicação

---

## Documentação Swagger

A **API Naruto 1** conta com a documentação swagger configurada no projeto, podendo se acessada, após subir a aplicação, no 
seguint endereço:
 ```
    http://localhost:8080/swagger-ui/index.html#/
 ```



## Considerações finais

- Certifique-se que as portas 8080 (app) e 5433 (Postgres) estão livres no seu sistema.
- Para limpar volumes e dados do banco, remova o volume `postgres-data` com:

```bash
docker volume rm postgres-data
```
---

##### Projeto no GitHub:  https://github.com/SauloHrodrigues/Desafio_Naruto01.git

## Autor:

### Nome: Saulo Henrique Rodrigues

##### LinkedIn: https://www.linkedin.com/in/saulohenriquerodrigues/