FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21

WORKDIR /statuspage

COPY --from=builder /app/target/StatusPage-0.0.1-SNAPSHOT.jar /statuspage.jar

ENTRYPOINT ["java","-jar","/statuspage.jar"]

EXPOSE 8080