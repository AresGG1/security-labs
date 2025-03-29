FROM maven:3.8-openjdk-17

WORKDIR /app

# Copy Maven wrapper files and pom.xml
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Download dependencies (this layer will be cached unless pom.xml changes)
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src/ src/

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
