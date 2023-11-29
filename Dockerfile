FROM maven:3.8.3-openjdk-16 AS builder

WORKDIR /app

COPY pom.xml .

COPY src src

RUN mvn package -DskipTests

FROM builder AS tester

FROM arm64v8/neo4j:latest

EXPOSE 7474 7473 7687

ENV NEO4J_AUTH=neo4j/neo4jPass
ENV NEO4J_DATABASE=billy


COPY neo4j.conf /etc/neo4j/

# Run Neo4j and execute tests
CMD neo4j start && mvn test

FROM maven:3.8.3-openjdk-16 AS final

# Set the working directory for the application
WORKDIR /app

# Copy Maven dependencies and JAR file from the 'builder' stage
COPY --from=builder /app/target/billy-bill-api-bs-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the image name and tag
LABEL image.name="billy-bill-api-bs-0.0.1"

# Start the Spring Boot application
CMD java -jar app.jar
