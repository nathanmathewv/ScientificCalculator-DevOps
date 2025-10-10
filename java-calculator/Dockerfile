FROM maven:3.9-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jre-slim

WORKDIR /app

COPY --from=build /app/target/scientific-calculator-1.0.0.jar /app/calculator.jar

EXPOSE 8080

CMD ["java", "-jar", "calculator.jar"]
