# API Spring Boot com PostgreSQL e Docker

Este projeto consiste em uma api simples construída com Spring Boot que utiliza o PostgreSQL como banco de dados. O projeto está containerizado usando o Docker para facilitar a implantação e o desenvolvimento.

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Maven

## Pré-requisitos

Antes de começar, você precisa ter as seguintes ferramentas instaladas:

- Java 17 ou superior (JDK)
- Maven (gerenciador de dependências)
- Docker e Docker Compose
- PostgreSQL (caso não esteja utilizando o Docker para o banco de dados)

## Instalação

### 1. Clonar o repositório

Clone este repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/api-spring-postgres.git
cd api-spring-postgres
```

### 2. Configuração do Banco de Dados (PostgreSQL)

O projeto está configurado para rodar com o PostgreSQL. Se você estiver usando o Docker, o banco de dados será automaticamente configurado para você, mas você também pode configurar o PostgreSQL manualmente.

#### Usando Docker

Se você deseja usar o Docker para rodar o PostgreSQL, basta executar o comando abaixo, que iniciará tanto o banco de dados quanto a aplicação:

```bash
docker-compose up
```

O Docker Compose irá configurar dois contêineres:

- PostgreSQL na porta 5432
- API Spring Boot na porta 8080

### Usando PostgreSQL localmente

Se preferir rodar o PostgreSQL localmente, você precisa configurar o banco de dados e as credenciais manualmente. Depois, no arquivo application.properties, altere as configurações de conexão com o banco para corresponder às suas.

O arquivo application.properties (localizado em src/main/resources/application.properties) já contém a configuração padrão para um banco de dados PostgreSQL.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/exemplo
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Gerar o JAR da Aplicação

Antes de rodar o Docker, você precisa gerar o arquivo JAR da aplicação. Para isso, basta rodar o comando Maven:

```bash
mvn clean install
```

Isso irá gerar o arquivo JAR em target/api-postgres-0.0.1-SNAPSHOT.jar.

### 4. Containerizar a Aplicação com Docker

Agora, você pode criar a imagem Docker para a sua aplicação:

```bash
docker build -t api-postgres .
```

Isso criará uma imagem Docker chamada api-postgres.

### 5. Rodar os Contêineres

Após a construção da imagem Docker, você pode rodar a aplicação com:

```bash
docker-compose up
```

Isso irá iniciar o banco de dados PostgreSQL e a aplicação na porta 8080.

## Endpoints da API

A API expõe os seguintes endpoints para interagir com o banco de dados:

### 1. Listar todas as pessoas

GET `/pessoas`

Retorna a lista de todas as pessoas cadastradas no banco de dados.

Exemplo de resposta:

```json
[
  {
    "id": 1,
    "nome": "João",
    "idade": 25
  },
  {
    "id": 2,
    "nome": "Maria",
    "idade": 30
  }
]
```

### 2. Buscar uma pessoa por ID

GET `/pessoas/{id}`

Retorna uma pessoa específica pelo ID.

Exemplo de resposta:

```json
{
  "id": 1,
  "nome": "João",
  "idade": 25
}
```

### 3. Criar uma nova pessoa

POST `/pessoas`

Cria uma nova pessoa no banco de dados. Envie os dados no corpo da requisição como JSON.

Exemplo de requisição:

```json
{
  "nome": "Carlos",
  "idade": 28
}
```

### 4. Excluir uma pessoa

DELETE `/pessoas/{id}`

Exclui uma pessoa do banco de dados pelo ID.

## Docker Compose

O arquivo docker-compose.yml configura os serviços Docker para rodar tanto o PostgreSQL quanto a API. Aqui está a configuração:

```yml
version: '3.8'

services:
  db:
    image: postgres:latest
    container_name: postgres-db
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: sua_senha
      POSTGRES_DB: exemplo
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data

  app:
    build: .
    container_name: api-postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/exemplo
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: sua_senha
    ports:
      - "8080:8080"
    depends_on:
      - db

volumes:
  postgres-data:
```

## Parar os Contêineres

Para parar os contêineres em execução, use:

```bash
docker-compose down
```

Ou, para parar e remover os contêineres, use:

```bash
docker-compose down --volumes --remove-orphans
```

## Contribuições

Se você deseja contribuir com o projeto, faça um fork, crie uma branch, faça suas alterações e envie um pull request.

## Licença

Este projeto é de código aberto e pode ser utilizado de acordo com a licença MIT.
