package com.arthurlamberti.cdb.infrastructure;

import com.arthurlamberti.cdb.infrastructure.config.WebServerConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//http://localhost:8080/api/swagger-ui/index.html
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(WebServerConfig.class, args);
    }
}