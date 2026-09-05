package com.example.cwLiveStreamingProvider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class CwLiveStreamingProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwLiveStreamingProviderApplication.class, args);
    }

}
