package org.haveagroup.xes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class XesApplication {

    public static void main(String[] args) {
        SpringApplication.run(XesApplication.class, args);
    }

}
