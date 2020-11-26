package com.serverhealthcheck.health.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthConfig {
    @Value("${server.check}")
    private String url;

    @Bean
    public HealthChecker healthChecker(RestTemplateBuilder restTemplateBuilder) {
        return HealthChecker.builder()
                .url(url)
                .restTemplate(restTemplateBuilder.build())
                .build();
    }

}
