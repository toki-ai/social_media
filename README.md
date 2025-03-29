# Social Media Web Application

A modern social media web application built with Spring Boot, featuring real-time messaging, user authentication, and social networking capabilities.

## Features

- 🔐 Secure user authentication with JWT
- 💬 Real-time messaging using WebSocket
- 👥 User profiles and social connections
- 📱 RESTful API endpoints
- 📚 OpenAPI documentation
- 🔄 Real-time updates
- 🛡️ Spring Security integration

## Tech Stack

- Java 17
- Spring Boot 3.3.3
- Spring Security
- Spring Data JPA
- MySQL 8.0
- WebSocket
- JWT Authentication
- Lombok
- Maven

## Prerequisites

- JDK 17 or later
- Maven 3.6+
- MySQL 8.0
- Docker (optional)

## Getting Started

### Local Development

1. Clone the repository:

```bash
git clone https://github.com/yourusername/social-media.git
cd social-media
```

2. Configure your MySQL database:

   - Create a new database
   - Update the database configuration in `application.properties`

3. Build the project:

```bash
./mvnw clean install
```

4. Run the application:

```bash
./mvnw spring-boot:run
```

### Docker Deployment

1. Build the Docker image:

```bash
docker build -t social-media .
```

2. Run the container:

```bash
docker run -p 8080:8080 social-media
```

## API Documentation

Once the application is running, you can access the OpenAPI documentation at:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/toki/socialmedia/
│   │       ├── config/         # Configuration classes
│   │       ├── controller/     # REST controllers
│   │       ├── model/          # Entity classes
│   │       ├── repository/     # JPA repositories
│   │       ├── service/        # Business logic
│   │       └── security/       # Security configurations
│   └── resources/
│       └── application.properties
```
