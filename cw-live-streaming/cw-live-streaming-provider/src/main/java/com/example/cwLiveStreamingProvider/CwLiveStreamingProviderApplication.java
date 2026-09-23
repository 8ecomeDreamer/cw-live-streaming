package com.example.cwLiveStreamingProvider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.cwLiveStreamingProvider.mapper")
public class CwLiveStreamingProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwLiveStreamingProviderApplication.class, args);
    }

}
