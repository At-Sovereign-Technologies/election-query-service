# election-query-service

## 1. Descripción

El Election Query Service es un microservicio de solo lectura encargado
de exponer información pública sobre elecciones, como listados y
consultas por ID.

Forma parte del lado de lectura bajo el enfoque CQRS y utiliza Redis
como capa de cache con mecanismos de resiliencia para garantizar
disponibilidad incluso ante fallos.

------------------------------------------------------------------------

## 2. Tecnologías

-   Java 21
-   Spring Boot 3.x
-   Spring Web
-   Spring Data JPA
-   PostgreSQL
-   Redis
-   Resilience4j (Circuit Breaker)
-   Flyway
-   Springdoc OpenAPI (Swagger)
-   Maven
-   JUnit 5
-   Mockito
-   JaCoCo

------------------------------------------------------------------------

## 3. Arquitectura

Microservicio basado en arquitectura por capas:

-   Controller: Manejo de endpoints REST
-   Service: Lógica de negocio y orquestación
-   Repository: Acceso a datos
-   Cache Adapter: Integración con Redis
-   Circuit Breaker: Manejo de fallos en cache
-   Mapper: Transformación de entidades a DTOs

------------------------------------------------------------------------

## 4. Estrategia de Cache

Se implementa el patrón cache-aside con resiliencia:

1.  Se consulta Redis
2.  Si no existe o falla, se consulta la base de datos
3.  Se almacena el resultado en cache
4.  En caso de fallo de Redis, el sistema continúa operando con DB

------------------------------------------------------------------------

## 5. Resiliencia (Circuit Breaker)

Se implementa Circuit Breaker con Resilience4j:

-   Detecta fallos en Redis
-   Evita llamadas innecesarias a servicios caídos
-   Permite fallback automático hacia la base de datos
-   Mejora la latencia en escenarios de fallo

Estados:

-   CLOSED → operación normal
-   OPEN → Redis deshabilitado temporalmente
-   HALF-OPEN → prueba de recuperación

------------------------------------------------------------------------

## 6. Versionamiento de API

/api/v1/\*

------------------------------------------------------------------------

## 7. Variables de entorno

Crear archivo `.env`:

DB_URL=jdbc:postgresql://localhost:5432/election_db\
DB_USER=election_user\
DB_PASSWORD=123456

REDIS_HOST=localhost\
REDIS_PORT=6379

PORT=8082

------------------------------------------------------------------------

## 8. Base de datos

CREATE DATABASE election_db;\
CREATE USER election_user WITH PASSWORD '123456';\
GRANT ALL PRIVILEGES ON DATABASE election_db TO election_user;

------------------------------------------------------------------------

## 9. Redis

sudo systemctl start redis-server\
redis-cli ping

Respuesta esperada: PONG

------------------------------------------------------------------------

## 10. Flyway

Ubicación de migraciones:

src/main/resources/db/migration

------------------------------------------------------------------------

## 11. Ejecución

export \$(grep -v '\^#' .env \| xargs)\
mvn spring-boot:run

------------------------------------------------------------------------

## 12. Swagger

http://localhost:8082/swagger-ui.html

------------------------------------------------------------------------

## 13. Endpoints

GET /api/v1/elections\
GET /api/v1/elections/{id}

------------------------------------------------------------------------

## 14. Respuestas

Éxito:

{ "id": 1, "name": "Elección Presidencial 2026", "status": "ACTIVE" }

Error 404:

{ "timestamp": "...", "status": 404, "error": "NOT_FOUND", "message":
"Election not found", "path": "/api/v1/elections/99" }

------------------------------------------------------------------------

## 15. Observabilidad

Logging estructurado:

-   CACHE HIT
-   CACHE MISS
-   CACHE STORE
-   CACHE ERROR
-   CACHE FALLBACK
-   Circuit Breaker events (OPEN, CLOSED, HALF-OPEN)

------------------------------------------------------------------------

## 16. Pruebas

El microservicio cuenta con pruebas unitarias:

-   Service: cache hit, cache miss, fallback
-   Controller: respuestas HTTP
-   Mapper: transformación de datos
-   Cache Adapter: comportamiento con Redis
-   Exception Handler: manejo de errores

------------------------------------------------------------------------

## 17. Cobertura

Cobertura total: 83%\
Cobertura lógica: \~100%

Clases no cubiertas: configuración y clase principal.

------------------------------------------------------------------------

## 18. Estado

Microservicio funcional, resiliente y listo para integración:

-   API REST operativa
-   PostgreSQL integrado
-   Redis con tolerancia a fallos
-   Circuit Breaker activo
-   Documentación Swagger
