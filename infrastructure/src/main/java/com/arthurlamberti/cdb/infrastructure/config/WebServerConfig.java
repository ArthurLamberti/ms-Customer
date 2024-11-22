package com.arthurlamberti.cdb.infrastructure.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.arthurlamberti.cdb")
@EnableFeignClients(basePackages = "com.arthurlamberti.cdb.infrastructure")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class WebServerConfig {
}
