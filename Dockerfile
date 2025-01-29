FROM maven:3.9-sapmachine-21 AS build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENV spring.datasource.url=jdbc:postgresql://postgres:5432/postgres?createDatabaseIfNotExist=true
ENTRYPOINT ["java", "-jar", "app.jar"]