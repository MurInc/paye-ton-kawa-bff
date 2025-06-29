# === Stage 1: Build the app ===
FROM maven:3.9.6-eclipse-temurin-17 as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build app
RUN mvn clean package -DskipTests

# Build container
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
