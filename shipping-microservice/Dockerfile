FROM maven:3.6.3-jdk-11 as build-stage
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:11.0.2-jre-slim AS prod-stage
COPY --from=build-stage /app/target/*.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djasypt.encryptor.password=orderingSecretKey","-Dspring.profiles.active=prod","-jar","/usr/local/lib/app.jar"]