package com.example.choyxona.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResDay {
    private Long id;
    private int dayNum;
    private String monthName;
    private Integer year;
}
