package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    public static final String URL_VALIDATE_PERSON = "http://localhost:8085/validate-person";
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
