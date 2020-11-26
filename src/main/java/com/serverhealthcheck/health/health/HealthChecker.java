package com.serverhealthcheck.health.health;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Data
@Builder
public class HealthChecker {
    private String url;
    private final RestTemplate restTemplate;


    public boolean checkServer() {
        try {
            ResponseEntity<Void> forEntity = restTemplate.getForEntity(url, Void.class);
            return forEntity.getStatusCode().is2xxSuccessful();
        }catch (Exception e){
            return false;
        }
    }
}
