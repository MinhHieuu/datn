package com.beeshop.sd44;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,

})
public class Sd44Application {

    public static void main(String[] args) {
        SpringApplication.run(Sd44Application.class, args);
    }

}
