# Twitch Discovery App (Frontend + Backend)

Discover and favorite Twitch games, streams, videos, and clips with a simple full‑stack app. Personalized recommendations included.

Live Demo: URL

Shortcuts: [Frontend](#frontend) • [Backend](#backend)

## Architecture

```
             Browser (SPA)
                   |
         http://localhost:3000
                   |
            CRA dev server
                   | (proxy)
                   v
     Spring Boot API @ :8080  ---->  Twitch Helix API
              |
              v
           MySQL (Docker)
```

## One‑Command Local Run

Run from repo root (zsh/bash), starts DB + backend + frontend:

```
# Start MySQL, backend (8080) in background, then frontend (3000)
cd twitch && docker compose up -d db && ./gradlew bootRun & cd ../twitchfe && npm start
```

Notes:
- Requires Docker, Java 21, Node.js 18+.
- Frontend proxies API calls to `http://localhost:8080` (configured in `twitchfe/package.json`).
- Stop processes with Ctrl+C; stop DB with `docker compose -f twitch/docker-compose.yml down`.

---

## Frontend

Location: `twitchfe/` (Create React App + Ant Design)

- Prerequisites: Node.js 18+ and npm
- Install deps: `cd twitchfe && npm install`
- Start dev: `npm start` (opens http://localhost:3000)
- Build: `npm run build`

Proxy to backend is defined in `twitchfe/package.json` as `"proxy": "http://localhost:8080/"`.

---

## Backend

Location: `twitch/` (Java 21, Spring Boot 3.5, Gradle)

Prerequisites:
- Java 21
- Docker (for MySQL)

Environment variables (Twitch OAuth2 Client Credentials):
- `TWITCH_CLIENT_ID`
- `TWITCH_CLIENT_SECRET`

Optional DB overrides (defaults in `application.yml`):
- `DATABASE_URL=localhost`
- `DATABASE_PORT=3306`
- `DATABASE_USERNAME=root`
- `DATABASE_PASSWORD=secret`
- `DATABASE_INIT=always`

Start sequence:
```
cd twitch
docker compose up -d db              # start MySQL 8 on 3306
export TWITCH_CLIENT_ID=...         # set your Twitch credentials
export TWITCH_CLIENT_SECRET=...
./gradlew bootRun                   # app on http://localhost:8080
```

Build & run JAR:
```
./gradlew bootJar
java -jar build/libs/twitch-0.0.1-SNAPSHOT.jar
```

Key endpoints:
- `GET /game?game_name={name}` — search games (or top games if omitted)
- `GET /search?game_id={id}` — streams/videos/clips for a game
- `GET /recommendation` — top or personalized recommendations
- Auth: `POST /register`, `POST /login`, `POST /logout`
- Favorites: `GET/POST/DELETE /favorite`

---

## Repository Layout

- `twitch/` — Backend (Spring Boot API, MySQL, Docker compose)
- `twitchfe/` — Frontend (React, CRA dev server with proxy)
- `.gitignore` — Ignore rules for dev/build artifacts
- `README.md` — This document
