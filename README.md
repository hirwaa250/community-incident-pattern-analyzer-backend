# Community Incident Pattern Analyzer - Backend

**Names:** Fabrice Hirwa
**Student ID:** 27484
**Project:** Community Incident Pattern Analyzer

## Steps to run the API

1. Install Java 21 and PostgreSQL.
2. Create the database:

   ```sql
   CREATE DATABASE community_incident_analyzer;
   ```

3. In `src/main/resources/application.properties` put your own PostgreSQL password in
   `spring.datasource.password` (user `postgres`, port `5432`).
4. Start the API from the project folder:

   ```
   ./mvnw spring-boot:run
   ```

   (on Windows: `mvnw.cmd spring-boot:run`). The API runs on `http://localhost:8081` and the tables
   are created automatically.
5. Import `community-incident-pattern-analyzer-midterm.postman_collection.json` in Postman and run
   the folders in order, starting with `Step 0 - Seed data` (use an empty database).
