# election-query-service

## 1. Descripción

El Election Query Service es responsable de exponer endpoints públicos de solo lectura relacionados con información de elecciones, como listado de elecciones y consulta por ID.

El servicio sigue un enfoque CQRS (lado de lectura) y utiliza Redis como capa de cache para optimizar el rendimiento mediante el patrón cache-aside.

---

## 2. Tecnologías

- Java 21
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- PostgreSQL
- Redis
- Flyway
- Springdoc OpenAPI (Swagger)
- Maven
- JUnit 5
- Mockito
- JaCoCo

---

## 3. Arquitectura

El servicio está diseñado como un microservicio de solo lectura dentro del sistema ciudadano.

Capas:

- Controller: Manejo de solicitudes HTTP
- Service: Lógica de negocio y cache
- Repository: Acceso a datos
- Cache Adapter: Redis
- Mapper: Entity → DTO

### Estrategia de cache

1. Consulta en Redis
2. Si no existe → consulta DB
3. Guarda resultado en cache

---

## 4. Versionamiento de API

/api/v1/*

---

## 5. Variables de entorno

Crear archivo `.env`:

DB_URL=jdbc:postgresql://localhost:5432/election_db  
DB_USER=election_user  
DB_PASSWORD=123456  

REDIS_HOST=localhost  
REDIS_PORT=6379  

PORT=8082  

---

## 6. Base de datos

CREATE DATABASE election_db;  
CREATE USER election_user WITH PASSWORD '123456';  
GRANT ALL PRIVILEGES ON DATABASE election_db TO election_user;  

---

## 7. Redis

sudo systemctl start redis-server  
redis-cli ping  

Respuesta esperada: PONG

---

## 8. Flyway

src/main/resources/db/migration

---

## 9. Ejecución

export $(grep -v '^#' .env | xargs)  
mvn spring-boot:run  

---

## 10. Swagger

http://localhost:8082/swagger-ui.html

---

## 11. Endpoints

GET /api/v1/elections  
GET /api/v1/elections/{id}

---

## 12. Respuestas

Éxito:

{
  "id": 1,
  "name": "Elección Presidencial 2026",
  "status": "ACTIVE"
}

Error 404:

{
  "timestamp": "...",
  "status": 404,
  "error": "NOT_FOUND",
  "message": "Election not found",
  "path": "/api/v1/elections/99"
}

---

## 13. Pruebas

El microservicio cuenta con pruebas unitarias para validar la lógica de negocio, manejo de cache y comportamiento de la API.

- Service: cache hit, cache miss, not found  
- Controller: respuestas 200, 400, 404  
- Mapper: conversión entity → DTO  
- Cache Adapter: interacción con Redis  
- Exception Handler: manejo de errores  

---

## 14. Cobertura

Cobertura total: 83%  
Cobertura lógica: ~100%

Clases no cubiertas: config y clase principal (sin lógica funcional).

---

## 15. Estado

Microservicio funcional, probado y listo para integración.