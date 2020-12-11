package com.example.choyxona.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResProduct {
    private UUID productClientId;
    private long productId;
    private String name;
    private double balance;
    private int amount;
//    private Double totalSum;
}
