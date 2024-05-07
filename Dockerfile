FROM maven:3.8.3-openjdk-16 AS builder

WORKDIR /app

COPY pom.xml .

COPY src src

RUN mvn package -DskipTests

FROM maven:3.8.3-openjdk-16 AS final

WORKDIR /app

COPY --from=builder /app/target/billy-bill-api-bs-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

LABEL image.name="billy-bill-api-bs-0.0.1"

CMD java -jar app.jar --spring.profiles.active=sandbox