FROM adoptopenjdk/openjdk11
COPY ./target/inventory-api*.jar inventory-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=production", "-jar", "/inventory-api.jar"]
