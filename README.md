# Pokémon API

A CRUD API to manage Pokémon built with Spring Boot, PostgreSQL, Docker, Liquibase, and Swagger.

## Features

- Full CRUD functionality
- Swagger UI for API testing
- PostgreSQL containerized with Docker
- Database migrations with Liquibase
- Basic Authentication and Unit Tests

## Setup

1. `docker-compose up -d`
2. Run the application via your IDE or `./mvnw spring-boot:run`
3. Access API: `http://localhost:8080/api/pokemons`
4. Access Swagger: `http://localhost:8080/swagger-ui.html`