FROM openjdk:8
EXPOSE 8084
ADD target/aa-data-fetch-svc-0.0.1-SNAPSHOT.jar aa-data-fetch-svc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Duser.timezone=UTC","-jar","/aa-data-fetch-svc-0.0.1-SNAPSHOT.jar"]
