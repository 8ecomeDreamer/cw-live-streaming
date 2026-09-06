package com.example.cwLiveStreamingApi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CwLiveStreamingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwLiveStreamingApiApplication.class, args);
    }

}
