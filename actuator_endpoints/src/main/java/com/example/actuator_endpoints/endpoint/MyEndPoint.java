package com.example.actuator_endpoints.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id="battcn")
public class MyEndPoint {
    @ReadOperation
    public Map<String, String> hello(){
        Map<String,String> result = new HashMap<>();
        result.put("author", "Levin");
        result.put("age", "24");
        result.put("email", "1837307557@qq.com");
        return null;
    }
}
