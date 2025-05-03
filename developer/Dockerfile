FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests

ENTRYPOINT ["java", "-jar", "target/developer-0.0.1-SNAPSHOT.jar"]
