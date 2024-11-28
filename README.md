<h2>Microserviço customer</h2>

<p align="center">
 <a href="#Objetivo">Objetivo</a> •
 <a href="#Tecnologias">Tecnologias</a> •
 <a href="#Como rodar">Como rodar</a> •
 <a href="#Rotas">Rotas</a> 
</p>

## Objetivo
Esse microserviço tem como objetivo principal gerir os usuários, controlando quando pode ou não haver uma compra de determinado papel

## Tecnologias
Para o desenvolvimento desse projeto, foi utilizado as seguintes tecnologias:
 - Java 17, com springboot framework
 - Banco de dados MySQL

## Como rodar
Para rodar esse serviço, podemos executar o docker-compose do projeto para subirmos o banco mySQL
```
docker-compose up -d
```
Após, basta executarmos as migrations para configurar o banco, e rodarmos a class Main com o profile development

## Rotas
O projeto dispoe das seguintes rotas, que podem ser testadas pelo ([swagger](http://localhost:8080/api/swagger-ui/index.html))

### GET /customers
- Essa rota serve para listarmos todos os usuarios. Não retorna informação de balance.

### POST /customers
- Rota disponibilizada para criarmos um usuario, passado no corpo da requisição

### GET /customers/{customerId}
- Rota utilizada para buscar determinado usuário. Faz a busca do saldo disponível no microserviço de customer_wallet
