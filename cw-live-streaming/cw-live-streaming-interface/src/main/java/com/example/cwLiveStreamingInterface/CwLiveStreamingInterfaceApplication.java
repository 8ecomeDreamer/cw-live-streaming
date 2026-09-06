package com.example.cwLiveStreamingInterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CwLiveStreamingInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwLiveStreamingInterfaceApplication.class, args);
    }

}
