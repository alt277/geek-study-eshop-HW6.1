package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ShopPictureServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopPictureServiceAppApplication.class, args);
    }

}
