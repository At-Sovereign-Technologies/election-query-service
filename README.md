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

```
/api/v1/*
```

---

## 5. Variables de entorno

Crear archivo `.env`:

```
DB_URL=jdbc:postgresql://localhost:5432/election_db
DB_USER=election_user
DB_PASSWORD=123456

REDIS_HOST=localhost
REDIS_PORT=6379

PORT=8082
```

---

## 6. Base de datos

### Crear DB

```sql
CREATE DATABASE election_db;
CREATE USER election_user WITH PASSWORD '123456';
GRANT ALL PRIVILEGES ON DATABASE election_db TO election_user;
```

---

## 7. Redis

```bash
sudo systemctl start redis-server
redis-cli ping
```

Respuesta esperada:

```
PONG
```

---

## 8. Flyway

Migraciones en:

```
src/main/resources/db/migration
```

Ejemplo:

### V1__init.sql

```sql
CREATE TABLE election (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    status VARCHAR(50),
    start_date TIMESTAMP,
    end_date TIMESTAMP
);
```

### V2__seed.sql

```sql
INSERT INTO election (name, status, start_date, end_date) VALUES
('Elección Presidencial 2026', 'ACTIVE', NOW(), NOW() + INTERVAL '1 day');
```

---

## 9. Ejecución

```bash
export $(grep -v '^#' .env | xargs)
mvn spring-boot:run
```

---

## 10. Swagger

```
http://localhost:8082/swagger-ui.html
```

---

## 11. Endpoints

### Obtener todas las elecciones

```
GET /api/v1/elections
```

### Obtener por ID

```
GET /api/v1/elections/{id}
```

---

## 12. Respuestas

### Éxito

```json
{
  "id": 1,
  "name": "Elección Presidencial 2026",
  "status": "ACTIVE"
}
```

### Error 404

```json
{
  "timestamp": "...",
  "status": 404,
  "error": "NOT_FOUND",
  "message": "Election not found",
  "path": "/api/v1/elections/99"
}
```

---

## 13. Consideraciones

- Servicio de solo lectura
- Sin autenticación
- Cache con Redis
- Datos provenientes de sistemas externos

---

## 14. Estado

Microservicio funcional con cache, migraciones y endpoints listos para integración.