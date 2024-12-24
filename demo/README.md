# Nome do Projeto

Este projeto é uma aplicação web desenvolvida com **Spring Boot**, **JPA/Hibernate** e **Thymeleaf**. O objetivo é demonstrar a integração dessas tecnologias para construir uma aplicação robusta e eficiente com funcionalidades de persistência de dados e uma interface de usuário dinâmica.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção de aplicações Java baseadas em Spring, que facilita o desenvolvimento com configurações mínimas e suporte a diversas funcionalidades.
- **JPA (Java Persistence API) / Hibernate**: Utilizado para gerenciamento de persistência de dados em um banco de dados relacional, proporcionando uma camada de abstração entre o banco e a aplicação.
- **Thymeleaf**: Motor de templates para renderização de páginas HTML dinâmicas. Integrado com o Spring Boot para gerar páginas dinâmicas baseadas em dados da aplicação.

## Funcionalidades

- **Persistência de dados**: A aplicação utiliza JPA/Hibernate para interagir com o banco de dados, permitindo salvar, editar, excluir e consultar informações.
- **Interface de usuário dinâmica**: As páginas da aplicação são renderizadas com Thymeleaf, proporcionando uma experiência interativa com dados provenientes do backend.
- **Estrutura RESTful**: A aplicação segue a arquitetura REST para exposição de serviços de backend e manipulação de dados.

## Como Executar

### Pré-requisitos

Antes de rodar o projeto, certifique-se de ter os seguintes programas instalados:

- **Java JDK 11 ou superior**
- **Maven** (ou usar a ferramenta de build integrada do Spring Boot)
- **Banco de dados relacional** (Exemplo: H2, MySQL, PostgreSQL)

### Passo a Passo

1. **Clone o repositório**:

    ```bash
    git clone https://github.com/seu-usuario/nome-do-repositorio.git
    cd nome-do-repositorio
    ```

2. **Compile o projeto com o Maven**:

    ```bash
    mvn clean install
    ```

3. **Inicie a aplicação**:

    ```bash
    mvn spring-boot:run
    ```

4. Abra seu navegador e acesse a aplicação em:

    ```bash
    http://localhost:8090
    ```

## Estrutura do Projeto

O projeto está organizado da seguinte forma:
- **src/main/java**: Contém os arquivos de código Java da aplicação.
    - **controller**: Controladores responsáveis por gerenciar as requisições HTTP.
    - **model**: Classes de modelo que representam entidades do banco de dados.
    - **repository**: Interfaces que permitem a interação com o banco de dados via JPA/Hibernate.
    - **service**: Lógica de negócios da aplicação.
- **src/main/resources**: Arquivos de configuração e recursos.
    - **templates**: Contém os templates Thymeleaf utilizados para renderizar as páginas HTML.
    - **application.properties**: Arquivo de configuração da aplicação, onde são definidos parâmetros como URL do banco de dados, porta, entre outros.

