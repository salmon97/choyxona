package com.example.choyxona.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqProduct {
    private UUID clientId;
    private long productId;
    private UUID productClientId;
    private int amount;

}
