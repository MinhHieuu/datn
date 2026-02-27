# Copilot instructions for this repository

Purpose: concise, actionable guidance so AI coding agents can be productive quickly.

- Project type: Spring Boot (Java) web application using Maven. Entry: `src/main/java/com/beeshop/sd44/Sd44Application.java`.
- Build & run:
  - Linux/macOS: `./mvnw spring-boot:run` or `./mvnw test`.
  - Windows: `mvnw.cmd spring-boot:run` or `mvnw.cmd test`.
- Key config: `src/main/resources/application.properties` (DB, JWT, upload path). Notable values:
  - `spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/sd44` (uses `MYSQL_HOST` env var)
  - `beeshop.upload-file.base-uri=file:///E:/Upload/` (file system dependency)
  - `jwt.secret`, `jwt.expiration`, `jwt.refresh` — used by `JWTService` and `SecurityConfig`.

Architecture & patterns (what to read first):
- Controllers: `src/main/java/com/beeshop/sd44/controller/*` — HTTP endpoints, keep changes here for routes.
- Services: `src/main/java/com/beeshop/sd44/service/*` — business logic (e.g., `JWTService`, `UserService`, `ImageService`).
- Repositories: `src/main/java/com/beeshop/sd44/repository/*` — Spring Data JPA interfaces named `*Repo`.
- Entities and DTOs: `src/main/java/com/beeshop/sd44/entity` and `src/main/java/com/beeshop/sd44/dto` — DTOs are split into `request/` and `response/`.

Conventions and project-specific notes:
- DTOs: incoming request shapes live under `dto/request`, responses under `dto/response`.
- Repositories use the `*Repo` suffix and are injected into services.
- Passwords: `BCryptPasswordEncoder` is provided by `SecurityConfig` and used during registration in `AuthController`.
- Authentication: OAuth2 Resource Server with JWTs. See `SecurityConfig.java` and `JWTService.java` for signing/verification patterns.
- File uploads: `FileController` and `ImageController` + `beeshop.upload-file.base-uri` define where files are stored.

Operational realities & gotchas to keep in mind:
- The app expects a MySQL database `sd44` by default; credentials are in `application.properties` (root:123456). Use `MYSQL_HOST` env var to override host.
- `beeshop.upload-file.base-uri` points to `E:/Upload/` on developer machines — ensure the path exists when running locally or adapt to a temp/test directory.
- CORS: configured in `SecurityConfig` (allowed origin lists). Frontend is expected at `http://localhost:3000` (inspect `SecurityConfig` for exact allowed origins).
- JWT secret is stored in `application.properties` — tests and local runs rely on this value.

Testing & debugging:
- Unit/integration tests: `mvnw test` (or `mvnw.cmd test` on Windows).
- Run in IDE: import as a Maven project and run `Sd44Application` or use Spring Boot run configuration.
- When debugging auth issues, check `JWTService` + `SecurityConfig` + `AuthController` sequence.

Where to add code:
- New REST endpoints -> add to `controller/` and corresponding service in `service/`.
- New DB models -> add entity in `entity/`, repository in `repository/`, and service methods to manage persistence.

Examples (common edits):
- Add an endpoint returning products: edit `ProductController`, call `ProductService`, which uses `ProductRepo`.
- Fix JWT behaviour: check `src/main/java/com/beeshop/sd44/service/JWTService.java` and `src/main/java/com/beeshop/sd44/config/SecurityConfig.java` together.

If you modify configs:
- Update `src/main/resources/application.properties` and note that local runs use values there; CI may provide env overrides.

Limitations of this file: documents only patterns discoverable in repo. Ask for clarifications about CI, deployment, or any missing infra (e.g., Docker, external storage) if needed.

If this needs to be merged into an existing copilot instructions file, preserve any team-specific rules and integrate the above project-specific sections.

---
Please review and tell me which areas need more detail (CI/CD, typical bug fixes, or code style rules).
