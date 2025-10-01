FROM maven:3.9-amazoncorretto-24 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:24-alpine

WORKDIR /app

COPY --from=build /app/target/todo-app-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/todo-app-0.0.1-SNAPSHOT.jar"]
