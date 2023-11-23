#Official Maven image as a base image
FROM maven:3.8.3-openjdk-16 AS builder

WORKDIR /app

COPY pom.xml .

COPY src src

RUN mvn package
FROM adoptopenjdk:16-jre-hotspot
WORKDIR /app
RUN mvn dependency:go-offline

COPY --from=builder /app/target/billy-bill-api-bs-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
