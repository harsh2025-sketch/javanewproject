# Weather-App

![Weather logo](./logo.svg)

A small Spring Boot web application that displays weather information using server-rendered Thymeleaf templates.

-This repository is a demo/sample Weather App built with:

- Java 17
# Weather-App

A small Spring Boot web application that displays weather information using server-rendered Thymeleaf templates.

This repository is a demo/sample Weather App built with:

- Java 17
- Spring Boot 3.3.x
- Thymeleaf templates
- Maven (wrapper included)

## Quick facts

- ArtifactId: `weather-app`
- Java version: 17 (see `pom.xml`)
- Build tool: Maven (wrapper `mvnw` / `mvnw.cmd` included)

## What it does

The app serves HTML pages (Thymeleaf) under `src/main/resources/templates` and static assets under `src/main/resources/static`. Use the web UI to search for or view weather information (implementation-specific details live in the Java sources under `src/main/java`).

## Prerequisites

- JDK 17 installed and JAVA_HOME set
- (Optional) Internet access if the app calls external weather APIs

## Build and run (Windows PowerShell)

From the project root run:

```powershell
# run with the included Maven wrapper
.\mvnw.cmd spring-boot:run

# or build a jar then run
.\mvnw.cmd -DskipTests package
java -jar target\*.jar

# run tests
.\mvnw.cmd test
```

For macOS / Linux replace `mvnw.cmd` with `./mvnw`.

## Project layout

- `src/main/java/com/example/weather_app/` - main Spring Boot app and controllers
- `src/main/resources/templates/` - Thymeleaf HTML templates (`index.html`, `weather.html`)
- `src/main/resources/static/` - static assets (CSS, images)
- `pom.xml` - Maven project file
- `mvnw`, `mvnw.cmd` - Maven wrapper

## Configuration

Application properties are in `src/main/resources/application.properties`. Update any API keys or endpoints there if the app uses an external weather service.

## Tests

Unit tests live under `src/test/java`. Run them with:

```powershell
.\mvnw.cmd test
```

## License

No license specified. Add a `LICENSE` file if you want to include one.

## Screenshots

Below are screenshots and an example image from the project to help visualize the UI and features.

<p align="center">
  <img src="./image/Screenshot%202025-11-09%20134713.png" alt="Screenshot 134713" width="350" />
  <img src="./image/Screenshot%202025-11-09%20134722.png" alt="Screenshot 134722" width="350" />
</p>

<p align="center">
  <img src="./image/Screenshot%202025-11-09%20135403.png" alt="Screenshot 135403" width="350" />
  <img src="./image/Screenshot%202025-11-09%20135416.png" alt="Screenshot 135416" width="350" />
</p>

<p align="center">
  <img src="./image/Screenshot%202025-11-09%20135429.png" alt="Screenshot 135429" width="350" />
  <img src="./image/Screenshot%202025-11-09%20135440.png" alt="Screenshot 135440" width="350" />
</p>

Example static image included in the app:

![App image](./src/main/resources/static/images/image.png)

---






