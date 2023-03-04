FROM openjdk:8
EXPOSE 8081
ADD target/aa-data-fetch-svc-0.0.1-SNAPSHOT.jar aa-data-fetch-svc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/aa-data-fetch-svc-0.0.1-SNAPSHOT.jar"]
