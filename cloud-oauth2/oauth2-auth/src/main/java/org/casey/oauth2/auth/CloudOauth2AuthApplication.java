package org.casey.oauth2.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.casey.oauth2.auth"})
public class CloudOauth2AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudOauth2AuthApplication.class, args);
    }

}
