package com.example.choyxona.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class ReqClient {
    private String name;
    private String telNum;
    private Long dayId;
    private long roomId;
    private Date date;
}