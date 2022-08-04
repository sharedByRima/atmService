package com.atm.atmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AtmserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmserviceApplication.class, args);
    }

    @Bean
    public RestTemplate resttemplate(){
        return new RestTemplate();
    }
}
