package com.technocrats.fidata;

import com.technocrats.fidata.services.AaSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication(exclude = {WebFluxAutoConfiguration.class})
public class AaDataFetchSvcApplication {

    @Autowired
    MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AaDataFetchSvcApplication.class, args);
    }


    @EventListener(ApplicationStartedEvent.class)
    public void doSome(){
        System.out.println(mongoTemplate.getCollectionNames());
    }
}
