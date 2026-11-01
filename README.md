# Community Incident Pattern Analyzer

**Student Name:** Fabrice Hirwa  
**Student ID:** 27484  
**Project:** Community Incident Pattern Analyzer

## 1. Project Description

The Community Incident Pattern Analyzer is a Spring Boot backend application designed to manage community incident reports, residents, response officers, cells, and incident types.

## 2. Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Postman

## 3. Project Structure

The application follows a layered structure:

- `controller` – Handles API requests.
- `domain` – Contains entity classes and enums.
- `repository` – Handles database operations.
- `service` – Contains application logic.

## 4. Database Configuration

The application uses PostgreSQL.

Update the database credentials in `src/main/resources/application.properties` to match your local PostgreSQL configuration.

## 5. Running the Application

**Requirements:** Java 21 and PostgreSQL.

1. Create the PostgreSQL database named `community_incident_analyzer`.
2. Configure the database connection in `application.properties`.
3. Open a terminal in the project directory.
4. Run:

```bash
.\mvnw.cmd spring-boot:run
```

The application runs at:

`http://localhost:8081`

## 6. API Testing

The REST API endpoints can be tested using Postman.

The Postman collection is provided in the project root:

`community-incident-pattern-analyzer-midterm.postman_collection.json`
