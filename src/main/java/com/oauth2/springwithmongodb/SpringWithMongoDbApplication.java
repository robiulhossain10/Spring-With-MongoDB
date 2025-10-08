package com.oauth2.springwithmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class SpringWithMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWithMongoDbApplication.class, args);
    }

}
