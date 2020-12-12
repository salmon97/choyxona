package com.example.choyxona.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class Test {

    @GetMapping("/hi")
    public HttpEntity<?> hi() {
        return ResponseEntity.ok("hello");
    }

    public void getEmployees() {
        final String uri = "https://choyxana.herokuapp.com/api/hi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    }
}
