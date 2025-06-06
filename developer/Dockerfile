FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml .
COPY src/ src/
COPY . .

RUN apk add --no-cache bash && \
    chmod +x mvnw && \
    ./mvnw clean install -DskipTests

ENTRYPOINT ["java", "-jar", "target/developer-0.0.1-SNAPSHOT.jar"]
