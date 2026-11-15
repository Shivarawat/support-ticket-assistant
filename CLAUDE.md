# support-ticket-assistant

Spring Boot REST API for support tickets. Learning project for a Salesforce AMTS interview, so the code is written to be explained, not just to work.

## Stack
- Spring Boot 4.1.1, Java 25, Maven (`./mvnw`)
- Spring Web MVC, Bean Validation, Spring Data JPA (Hibernate), PostgreSQL 17
- Base package `com.shiv.support_ticket_assistant`: `model`, `dto`, `repository`, `service`, `controller`, `exception`
- Code style: 4-space indent; DTOs are Java records; constructor injection; short comments only where they explain *why*

## Run
```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-25.jdk/Contents/Home   # default `java` is a Salesforce placeholder
./mvnw spring-boot:run      # app on :8080
./mvnw test
```
Database: Docker container `ticket-postgres` (postgres:17-alpine, volume `ticket-pgdata`) on **localhost:5433**, db `tickets`, user/password `ticket`/`ticket`. Start it with `docker start ticket-postgres`. Port 5433 because another project's Postgres uses 5432.

Before starting the app yourself, check that port 8080 is free (`lsof -nP -iTCP:8080 -sTCP:LISTEN`), and only stop processes you started.

## Git
Public repo: https://github.com/Shivarawat/support-ticket-assistant (branch `main`). Commit and push at the end of each session that changes the code.
