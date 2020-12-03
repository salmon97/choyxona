package com.example.choyxona.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResRoom {
    private UUID clientId;
    private Long roomId;
    private Long dayId;
    private String status;
    private String name;
    private String capacity;
    private String date;
}
