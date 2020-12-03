package com.example.choyxona.controller;

import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.payload.ReqMonth;
import com.example.choyxona.service.MonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/month")
public class MonthController {


    @Autowired
    MonthService monthService;

    @GetMapping("/getInfoMonth/{id}")
    public HttpEntity<?> infoMonth(@PathVariable int id) {
        return ResponseEntity.ok(monthService.infoProClientMonth(id));
    }

    @GetMapping("/get")
    public HttpEntity<?> getMonth() {
        return ResponseEntity.ok(new ApiResponse("ok", true, monthService.resMonths()));
    }

    @PostMapping("/addAndEdit")
    public HttpEntity<?> addMonth(@RequestBody ReqMonth reqMonth) {
        if (reqMonth.getId() == null) {
            ApiResponse apiResponse = monthService.addMonth(reqMonth);
            return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
        } else {
            ApiResponse apiResponse = monthService.editMonth(reqMonth);
            return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
        }
    }

//    @PutMapping("/edit")
//    public HttpEntity<?> editMonth(@RequestBody ReqMonth reqMonth) {
//        return ResponseEntity.ok(monthService.editMonth(reqMonth));
//    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(monthService.delete(id));
    }

}