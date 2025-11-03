# Twitch Recommendation Backend

A Spring Boot backend that recommends Twitch streams, videos, and clips, with user favorites, caching, and a bundled static front-end.

## Summary
- Discover trending and personalized Twitch content via a clean REST API.
- Integrates with Twitch Helix API using OAuth2 Client Credentials.
- Stores users, favorites, and items in MySQL; leverages Caffeine cache to reduce API latency.
- Ships with a prebuilt single-page app served from the same origin for simple local usage.

## Architecture
```mermaid
flowchart LR
  B[Browser (SPA)] -- HTTP/JSON --> G[Spring Boot App]
  subgraph Spring Boot
    G --> C[Controllers\\nGame/Item/User/Favorite/Recommendation]
    C --> S[Services\\nTwitchService\\nUserService\\nFavoriteService\\nRecommendationService]
    S --> F[Feign Client\\nTwitchApiClient]
    S --> J[Spring Data JDBC]
    G -. Caffeine .-> X[(In-Memory Cache)]
  end
  F -- OAuth2 Client Credentials --> T[Twitch Helix API]
  J --> M[(MySQL)]
```

## Key Technologies
- Java 21, Spring Boot 3.5
- Spring Web, Spring Security (form login), Spring Data JDBC
- Spring Cloud OpenFeign, OAuth2 Client (Twitch)
- Caffeine cache
- MySQL (Dockerized via `docker-compose`)
- Gradle build

## API Overview
- `GET /game?game_name={name}` — Search games by name; if omitted, returns top games.
- `GET /search?game_id={id}` — Streams/videos/clips for a game (grouped by type).
- `GET /recommendation` — Recommend content; personalized when logged in, otherwise top-based.
- Favorites (requires login):
  - `GET /favorite` — List favorites grouped by type.
  - `POST /favorite` — Set favorite. Body: `{ "favorite": { ...ItemEntity } }`
  - `DELETE /favorite` — Unset favorite. Body: `{ "favorite": { "twitch_id": "..." } }`
- Auth & users:
  - `POST /register` — Body: `{ "username", "password", "first_name", "last_name" }`
  - `POST /login` — Spring Security form login (`username`/`password` form fields). Returns 204 on success.
  - `POST /logout` — Returns 204 on success.

Static SPA is served from `src/main/resources/public` (`/`, `/index.html`, `/static/**`).

## Local Setup
- Prereqs: Java 21, Gradle wrapper, Docker (for MySQL).
- Start MySQL:
  - `docker compose up -d db`
  - Defaults: database `twitch`, user `root`, password `secret` on `localhost:3306`.
  - Schema initializes automatically via `src/main/resources/database-init.sql`.
- Configure Twitch OAuth (recommended: override via environment variables):
  - `SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_TWITCH_CLIENT_ID`
  - `SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_TWITCH_CLIENT_SECRET`
- Optional DB overrides (defaults shown):
  - `DATABASE_URL=localhost`
  - `DATABASE_PORT=3306`
  - `DATABASE_USERNAME=root`
  - `DATABASE_PASSWORD=secret`
  - `DATABASE_INIT=always` (controls schema init)

### Seed Data
- `DevelopmentTester` inserts a `user0` row with a plain password for schema smoke tests. Use the `/register` endpoint (or UI modal) to create login-ready accounts with encoded passwords and authorities.

## Run Locally
- With Gradle: `./gradlew bootRun`
- Or build and run the JAR:
  - `./gradlew bootJar`
  - `java -jar build/libs/twitch-0.0.1-SNAPSHOT.jar`
- App listens on `http://localhost:8080`.

## Test
- Unit and context tests: `./gradlew test`

## App Runner URL
- URL: https://ye5mddx4t2.us-east-2.awsapprunner.com/

## Project Structure
- `src/main/java/com/laioffer/twitch` — Spring Boot app (controllers, services, config).
- `src/main/resources/public` — Prebuilt SPA assets served by Spring Boot.
- `src/main/resources/application.yml` — App config (override via env/profiles).
- `src/main/resources/database-init.sql` — MySQL schema init script.
- `Dockerfile`, `docker-compose.yml` — Containerization and local DB.
- `build.gradle` — Dependencies and build config.

---
