package com.example.actuator_endpoints.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("my1")
public class MyHealthIndicator implements HealthIndicator {
    private static final String VERSION = "v1.0.0";

    @Override
    public Health health() {
        int code = check();
        if(code != 0){
            return Health.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
        return Health.down().withDetail("code", code).withDetail("version", VERSION).up().build();
    }
    private int check(){
        return 0;
    }
}
