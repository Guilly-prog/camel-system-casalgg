# Camel Service - Casalgg

Este projeto é uma aplicação Java que utiliza **Apache Camel** para expor endpoints RESTful e realizar operações CRUD (Create, Read, Update, Delete) em uma entidade de "Artigos". A aplicação está configurada para rodar em um ambiente Spring Boot, com integração usando o componente **Camel Servlet** para processar requisições HTTP.

## Sumário
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração do Ambiente](#configuração-do-ambiente)

## Tecnologias Utilizadas
- **Java 17**
- **Apache Camel 4.6.0**
- **Spring Boot 3.3.1**
- **Maven**
- **Tomcat Embedded**
- **Jackson (para serialização/deserialização JSON)**

## Configuração do Ambiente

Certifique-se de que você possui as seguintes ferramentas instaladas:

- **JDK 17+**
- **Maven** (para gerenciamento de dependências)
- **Postman** (ou outra ferramenta para testar as APIs REST)

### Arquivo de Configuração

A aplicação utiliza o arquivo `application.yml` para configurar os endpoints do Camel:

```yaml
camel:
  component:
    servlet:
      mapping:
        contextPath: /casalgg/*
