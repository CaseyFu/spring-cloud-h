package org.casey.seata.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.casey.seata.openfeign"})
public class CloudBusinessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudBusinessServiceApplication.class, args);
    }

}
