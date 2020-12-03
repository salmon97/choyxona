package com.example.choyxona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ChoyxonaApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ChoyxonaApplication.class, args);
    }

//    extends SpringBootServletInitializer
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(ChoyxonaApplication.class);
//    }
}
