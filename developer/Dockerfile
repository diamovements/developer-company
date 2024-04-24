FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/developer-0.0.1-SNAPSHOT.jar dev.jar
ENTRYPOINT ["java","-jar","/dev.jar"]