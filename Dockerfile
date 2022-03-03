FROM openjdk:11.0.13

COPY "./target/project1NTTData-0.0.1-SNAPSHOT.jar" "app.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]