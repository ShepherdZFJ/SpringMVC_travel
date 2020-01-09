package com.shepherd.springmvc_travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringmvcTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcTravelApplication.class, args);
    }

}
