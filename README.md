# Blog Platform API

This project is a RESTful Blog API built with Spring Boot.

It supports user authentication using JWT tokens and provides endpoints
for managing posts, categories, and tags.

## Installation

Clone the repository:

git clone https://github.com/yourusername/Blog-Platform-API.git

Navigate into the project directory:

cd Blog-Platform-API

Run the application:

./mvnw spring-boot:run

## Requirements
- Java 21
- Maven
- Docker (for PostgreSQL container)
- Docker Compose

## Database
PostgreSQL runs inside a Docker container via Docker Compose
for development environments.

H2 in-memory database is used automatically during testing.

### Running PostgreSQL with Docker

Start the database using Docker Compose: `docker compose up -d`

Stopping docker services: `docker compose down`

### Running Tests
Run unit tests with: `./mvnw clean test`

## Environment Variables
The following environment variables must be configured:
- POSTGRES_PASSWORD=YOUR_PASSWORD
- POSTGRES_USER=YOUR_USERNAME
- POSTGRES_DB=YOUR_DB_NAME
- jwt.secret=YOUR_256_BIT_SECRET_KEY_HERE_MAKE_IT_AT_LEAST_32_BYTES_LONG

## App Features:
- User registration and authentication
- JWT-based stateless authentication
- Secure password hashing using BCrypt
- Public and protected endpoints
- Draft post protection
- Category and tag management

## Usage
### 1.Register a user
POST /api/v1/auth/register
```json 
{
"name": "John",
"email": "john@mail.com",
"password": "123456"
}
```
### 2. Login and get token
POST /api/v1/auth/login

Request:
```json
{
  "email": "john@mail.com",
  "password": "123456"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

**After authentication, use the JWT token to access protected endpoints.**
### 3. Get all posts (public)
GET /api/v1/posts

Optional filters:
* GET /api/v1/posts?categoryId=<uuid>
* GET /api/v1/posts?tagId=<uuid>

Response:
```json
[
  {
    "id": "uuid",
    "title": "Post title",
    "content": "Post content",
    "author": "John",
    "createdAt": "2026-01-01"
  }
]
```

## Architecture

The application follows a layered architecture:

`Controller → Service → Repository → Database`

* Controllers handle HTTP requests and responses using DTOs.
* Service layer contains business logic and coordinates persistence operations.
* Repositories manage database interaction using Spring Data JPA.

Security is implemented using a custom JWT authentication filter
integrated into the Spring Security filter chain.

Authentication Flow:<br>
`Request → JWT Filter → Security Context → Controller`