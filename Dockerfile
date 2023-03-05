FROM openjdk:8
EXPOSE 8081
ADD target/aa-data-fetch-svc-0.0.1-SNAPSHOT.jar aa-data-fetch-svc-0.0.1-SNAPSHOT.jar
ENV user=user
ENV pwd=pwd
ENV CLIENT_API_KEY=client_api_key
ENTRYPOINT ["java","-Duser.timezone=UTC -DCLIENT_API_KEY=$CLIENT_API_KEY -Duser=$user -Dpwd=$pwd","-jar","/aa-data-fetch-svc-0.0.1-SNAPSHOT.jar"]
