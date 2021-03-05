package com.example.choyxona.controller;

import com.example.choyxona.entity.Product;
import com.example.choyxona.entity.ProductKg;
import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/get")
    public HttpEntity<?> getMonth() {
        return ResponseEntity.ok(new ApiResponse("ok", true, productService.list()));
    }

    @PostMapping("/addAndEdit")
    public HttpEntity<?> addMonth(@RequestBody Product product) {
        productService.saveEdit(product);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getKg")
    public HttpEntity<?> getMonthKg() {
        return ResponseEntity.ok(new ApiResponse("ok", true, productService.listKg()));
    }

    @PostMapping("/addAndEditKg")
    public HttpEntity<?> addMonthKg(@RequestBody ProductKg productKg) {
        productService.saveEditKg(productKg);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/deleteKg/{id}")
    public HttpEntity<?> deleteKg(@PathVariable long id) {
        productService.deleteKg(id);
        return ResponseEntity.ok("ok");
    }

}