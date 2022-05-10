FROM maven:3.6.3-jdk-11 as build-stage
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11.0.2-jre-slim
COPY --from=build-stage /app/target/microservice-kafka-shipping-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Dspring.profiles.active=prod -Xmx400m -Xms400m  -XX:TieredStopAtLevel=1 -noverify -jar microservice-kafka-shipping-0.0.1-SNAPSHOT.jar
EXPOSE 8080
