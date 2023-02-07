# Votação Pautas

Desafio técnico de uma aplicação de votação de Pautas.

## Funcionalidades

- Cadastrar/Alterar/Excluir Pauta
- Cadastrar Usuário-Associado
- Abrir Sessão de votação
- Votar em Pautas
- Consultar resultado de votação
- Verificação em api externa se um CPF está apto para votar(Tarefa Bônus 1)
- Públicação e Consumo do resultado da votação após o encerramento, em um tópico do ActiveMQ(Tarefa Bônus 2)
- API versionada e documentada (Tarefa Bônus 4)

## Requisitos

- Java JDK 11
- Apache Maven >= 3.6
- Docker
- Docker Compose
- Git

## Principais tecnologias utilizadas

- Java 11
- Spring
- ActiveMQ message broker
- Postgress database
- Maven
- Quartz
- Swagger

## Manual de Configuração
```
$ git clone git@github.com:proogramadorJ/pauta-votacao.git

$ cd pauta-votacao
```

Para executar a aplicação:

```
$ docker-compose up
```
O comando acima vai criar os 4 containers docker necessarios para funcionamento da aplicação. Descrição dos containers abaixo:  
### pauta-votacao-service:  
Container que possui a api votacao-pauta 
### consultacpf-service: 
Container da api externa de validação de CPF. O endereço informado na documentação do desáfio tecnico não
está funcionando, então eu criei um microserviço para atuar como essa api externa
### database-service
Container do banco de dados postgres
### activemq-service
Container do message broker activemq

## Criação do tópico no ActiveMQ
Para que seja póssivel públicar o resultado da votação após o encerramento da sessão, é necessario criar um tópico no
activeMQ.  

No navegador de sua escolha acesse : http://localhost:8161/admin/topics.jsp .  
O site vai solicitar a credências de acesso abaixo  
usuario: admin  
senha: admin  

Após fazer o login será exibida uma tela com a listagem de topicos existentes. Para criar um novo tópico é bem simples:  
Preencha o campo 'Topic Name' com o nome do tópico que deseja criar, no nosso caso informe o valor "resultadovotacaoTopic".  
Depois clique no botão "create" e o tópico será criado e já está pronto para ser utilizado pela aplicação.


## Documentação da API
Para saber quais são os serviços disponiveis na api e suas respectivas URLs, acesse:

http://localhost:8080/swagger-ui.html#/