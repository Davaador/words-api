# Words API

A REST API for an English learning app for software developers.
Built with Spring Boot and PostgreSQL.

## Features
- Get all words: `GET /api/words`
- Filter by category: `GET /api/words?category=css`
- Add a word (admin only): `POST /api/words`
- Each word has a category and a level (easy, medium, hard)

## Tech stack
- Java 21, Spring Boot, Spring Data JPA
- PostgreSQL (H2 for local development)
- Docker, deployed on Render

## Run locally
    ADMIN_KEY=your_secret_key ./mvnw spring-boot:run

Then open http://localhost:8080/api/words

## Environment variables
| Name | Description |
|---|---|
| `DB_URL` | JDBC URL of the database |
| `DB_USER` | Database username |
| `DB_PASSWORD` | Database password |
| `ADMIN_KEY` | Secret key for the POST endpoint |

## Example response
    [
      { "id": 1, "en": "tag", "mn": "шошго",
        "category": "web", "level": "easy" }
    ]

## Clients
- PWA (HTML, CSS, JavaScript)
- Flutter app (Android)