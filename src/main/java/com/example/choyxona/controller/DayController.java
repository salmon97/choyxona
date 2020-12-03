package com.example.choyxona.controller;

import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/day")
public class DayController {

    @Autowired
    DayService dayService;

    @GetMapping("get/{id}")
    public HttpEntity<?> getDays(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse("ok", true, dayService.resDays(id)));
    }

    @GetMapping("/getInfoDay/{id}")
    public HttpEntity<?> infoMonth(@PathVariable int id) {
        return ResponseEntity.ok(dayService.infoProClientDay(id));
    }
}
