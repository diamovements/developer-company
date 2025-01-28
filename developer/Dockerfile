
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar


ENV DATABASE_URL=jdbc:postgresql://service-db/developer
ENV DATABASE_USER=username
ENV DATABASE_PASSWORD=password

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
