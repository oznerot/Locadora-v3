# Locadora-v3
# Sistema-Locacao-Bicicletas - REST API

*Caio Ueda Sampaio - RA : 802215*

*Gabriel Pandolfi Correa dos Santos - RA : 769831*

*Pedro Henrique Borges - RA : 804071*

*Vitor Enzo Araujo Costa - RA : 802123* 

O objetivo desse projeto é desenvolver um sistema de Locacao de Bicicletas implementando um CRUD de Clientes,
e Locadoras de Bicicletas e uma lista das locacoes atravrés de requisitos/funcionalidades providas em uma REST API 

O sistema deve incorporar os seguintes requisitos:

- REST API -- CRUD de clientes
    - Cria um novo cliente [**C**reate - **CRUD**]
        POST http://localhost:8080/clientes
        Body: raw/JSON (application/json)
    - Retorna a lista de clientes [**R**ead - **CRUD**]
        GET http://localhost:8080/clientes
    - Retorna o cliente de id = {id} [**R**ead - ****CRUD****]
        GET http://localhost:8080/clientes/{id}
    - Atualiza o cliente de id = {id} [**U**pdate - **CRUD**]
        PUT http://localhost:8080/clientes/{id}
        Body: raw/JSON (application/json)
    - Remove o cliente de id = {id} [**D**elete - **CRUD**]
    DELETE http://localhost:8080/clientes/{id}

- REST API -- **CRUD** de locadoras
    - Cria uma nova locadora [**C**reate - **CRUD**]
        POST http://localhost:8080/locadoras
        Body: raw/JSON (application/json)
    - Retorna a lista de locadoras [**R**ead - **CRUD**]
        GET http://localhost:8080/locadoras
    - Retorna a locadora de id = {id} [**R**ead - **CRUD**]
        GET http://localhost:8080/locadoras/{id}
    - Retorna a lista de todas as locadoras da cidade de nome = {nome}
        GET http://localhost:8080/locadoras/cidades/{nome}
    - Atualiza a locadora de id = {id} [**U**pdate - **CRUD**]
    PUT http://localhost:8080/locadoras/{id}
    Body: raw/JSON (application/json)
    - Remove a locadora de id = {id} [**D**elete - **CRUD**]
    DELETE http://localhost:8080/locadoras/{id}

- REST API -- Retorna a lista de locações [**R**ead - **CRUD**]
GET http://localhost:8080/locacoes

- REST API -- Retorna a locação de id = {id} [**R**ead - **CRUD**]
GET http://localhost:8080/locacoes/{id}

- REST API -- Retorna a lista das locações do cliente de id = {id} [**R**ead - **CRUD**]
GET http://localhost:8080/locacoes/clientes/{id}

- REST API -- Retorna a lista de locações da locadora de id = {id} [**R**ead - **CRUD**]
GET http://localhost:8080/locacoes/locadoras/{id}

## Arquitetura

Modelo-Visão-Controlador (MVC)

## Tecnologias

- Spring MVC (Controladores REST), Spring Data JPA, Spring Security & Thymeleaf (Lado
Servidor)


## Ambiente de Desenvolvimento

- A compilação e o deployment devem ser obrigatoriamente realizados via Maven.
- Os arquivos fonte do sistema devem estar hospedados obrigatoriamente em um repositório (preferencialmente GitHub).

## Notas

1. **CRUD**: Create, Read, Update & Delete.
