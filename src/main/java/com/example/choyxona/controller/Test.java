package com.example.choyxona.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Test {

    @GetMapping("/hi")
    public HttpEntity<?> hi() {
        return ResponseEntity.ok("hello");
    }
}
