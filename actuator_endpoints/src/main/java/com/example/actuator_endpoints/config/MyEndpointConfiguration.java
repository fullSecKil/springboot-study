package com.example.actuator_endpoints.config;

import com.example.actuator_endpoints.endpoint.MyEndPoint;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyEndpointConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public MyEndPoint myEndPoint(){
        return new MyEndPoint();
    }
}
