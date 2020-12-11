package com.example.choyxona.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqResServiceClient {
    private UUID id;
    private UUID clientId;
    private String definition;
    private Double price;
    private Double cost;

    public ReqResServiceClient(UUID id, String definition, Double price) {
        this.id = id;
        this.definition = definition;
        this.price = price;
    }
}