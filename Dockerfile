FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/c322-spring2024-homework2-0.0.1-SNAPSHOT.jar homework6.jar
ENTRYPOINT ["java", "-jar", "homework6.jar"]
