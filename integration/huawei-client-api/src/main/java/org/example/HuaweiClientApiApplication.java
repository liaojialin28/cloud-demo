package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.mangbo.client")
public class HuaweiClientApiApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(HuaweiClientApiApplication.class, args);

    }
}