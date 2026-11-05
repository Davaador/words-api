# Words API

A REST API for an English learning app for software developers.
Built with Spring Boot and PostgreSQL.

## Features
- Get all words: `GET /api/words`
- Filter by category: `GET /api/words?category=css`
- Add a word (admin only): `POST /api/words`
- Each word has a category and a level (easy, medium, hard)
- Check English sentences with AI: `POST /api/check`

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
| `ANTHROPIC_API_KEY` | Claude API key (used only on the server) |

## Example response
    [
      { "id": 1, "en": "tag", "mn": "шошго",
        "category": "web", "level": "easy" }
    ]

## Clients
- PWA (HTML, CSS, JavaScript)
- Flutter app (Android)

## AI sentence checker
`POST /api/check` checks an English sentence with the Claude API.

Request:

    { "sentence": "My app run on my phone." }

Response:

    { "feedback": "Corrected: My app runs on my phone.\nWhy: ...\nМонгол: ..." }

Safety:
- The API key is stored in an environment variable, never in the code.
- Rate limit: 5 requests per minute per IP and 500 requests per day in total.
- A sentence can have at most 300 characters.
- The system prompt asks the model to treat the sentence only as text to check.

![Java CI](https://github.com/Davaador/words-api/actions/workflows/ci.yml/badge.svg)