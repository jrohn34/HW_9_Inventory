FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/ducks-service-0.0.1-SNAPSHOT.jar guitar-inventory-management.jar
ENTRYPOINT ["java", "-jar", "guitar-inventory-management.jar"]