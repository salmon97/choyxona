package com.example.choyxona.controller;

import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.payload.ReqClient;
import com.example.choyxona.payload.ReqProduct;
import com.example.choyxona.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/registerClient")
    public HttpEntity<?> registerClient(@RequestBody ReqClient reqClient) {
        ApiResponse apiResponse = clientService.registerClient(reqClient);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/sendCheck/{id}")
    public HttpEntity<?> sendCheck(@PathVariable UUID id) throws TelegramApiException {
        ApiResponse apiResponse = clientService.sendCheck(id);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("delete/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }

    @GetMapping("getClient/{id}")
    public HttpEntity<?> getClient(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.resClient(id));
    }

    @GetMapping("getProducts/{id}")
    public HttpEntity<?> getProducts(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.resProductClient(id));
    }

    @PostMapping("addProduct")
    public HttpEntity<?> addProduct(@RequestBody ReqProduct reqProduct) {
        if (reqProduct.getProductClientId() != null) {
            return ResponseEntity.ok(clientService.editProduct(reqProduct));
        } else {
            return ResponseEntity.ok(clientService.addProduct(reqProduct));
        }
    }

    @DeleteMapping("delete/proClient/{id}/{clientId}")
    public HttpEntity<?> deleteProductClient(@PathVariable UUID id, @PathVariable UUID clientId) {
        return ResponseEntity.ok(clientService.deleteProClient(id, clientId));
    }

    //Product kg client start
    @GetMapping("getProductsKg/{id}")
    public HttpEntity<?> getProductsKg(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.resProductKgClient(id));
    }

    @PostMapping("addProductKg")
    public HttpEntity<?> addProductKg(@RequestBody ReqProduct reqProduct) {
        if (reqProduct.getProductClientId() != null) {
            return ResponseEntity.ok(clientService.editProductClientKg(reqProduct));
        } else {
            return ResponseEntity.ok(clientService.addProductClientKg(reqProduct));
        }
    }

    @DeleteMapping("delete/proClientKg/{id}/{clientId}")
    public HttpEntity<?> deleteProductClientKg(@PathVariable UUID id, @PathVariable UUID clientId) {
        return ResponseEntity.ok(clientService.deleteProKgClient(id, clientId));
    }
}
