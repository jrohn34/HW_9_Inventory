FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/Homework2-0.0.1.jar Homework2.jar
ENTRYPOINT ["java", "-jar", "Homework2.jar"]
