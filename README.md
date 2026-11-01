# Support Ticket Assistant

A REST API for support tickets, built with Spring Boot and PostgreSQL.

## Tech stack

- Java 25, Spring Boot 4.1
- Spring Web MVC, Bean Validation
- Spring Data JPA (Hibernate)
- PostgreSQL 17 (Docker)
- Maven

## Features

- Ticket CRUD: create, list, get, update, delete
- Filter tickets by status: `GET /api/tickets?status=OPEN`
- Request validation with `@Valid`, with per-field error messages
- Global error handling with `@RestControllerAdvice`, returning one JSON error shape
- Layered design: controller → service → repository, with DTOs (Java records) at the API boundary

## API

| Method | Endpoint | Description | Success |
|---|---|---|---|
| `POST` | `/api/tickets` | Create a ticket | `201` |
| `GET` | `/api/tickets` | List tickets (optional `?status=`) | `200` |
| `GET` | `/api/tickets/{id}` | Get one ticket | `200` |
| `PUT` | `/api/tickets/{id}` | Replace a ticket's title, description, priority, status | `200` |
| `DELETE` | `/api/tickets/{id}` | Delete a ticket | `204` |

Priority: `LOW`, `MEDIUM`, `HIGH`, `URGENT`. Status: `OPEN`, `IN_PROGRESS`, `RESOLVED`, `CLOSED`.

Errors share one shape:

```json
{ "status": 400, "message": "Validation failed", "fieldErrors": { "title": "title is required" } }
```

## Running locally

Start PostgreSQL in Docker:

```bash
docker run -d --name ticket-postgres \
  -e POSTGRES_USER=ticket -e POSTGRES_PASSWORD=ticket -e POSTGRES_DB=tickets \
  -p 5433:5432 -v ticket-pgdata:/var/lib/postgresql/data \
  postgres:17-alpine
```

Start the app (requires Java 25):

```bash
./mvnw spring-boot:run
```

Try it out:

```bash
curl -i -X POST localhost:8080/api/tickets -H 'Content-Type: application/json' \
  -d '{"title":"Login broken","description":"500 on /login","priority":"HIGH"}'
curl localhost:8080/api/tickets?status=OPEN
```

The database credentials in `application.properties` are for the local Docker container only.

## Roadmap

- Integration tests
- Flyway migrations
- AI features with Spring AI (ticket summarization and classification)
