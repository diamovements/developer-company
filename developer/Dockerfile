#FROM openjdk:17
#VOLUME /tmp
#EXPOSE 8080
#COPY target/developer-0.0.1-SNAPSHOT.jar dev.jar
#ENTRYPOINT ["java","-jar","/dev.jar"]

FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
COPY src/main/resources/static /app/src/main/resources/static
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]