package com.example.choyxona.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResClient {
    private UUID id;
    private Timestamp registerAt;
    private String name;
    private String telNum;
    private String status;
    private String bookedDate;
    private Long chatId;
}
