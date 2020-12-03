package com.example.choyxona.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResMonth {
    private Long id;
    private String nameEng;
    private String nameUz;
    private Integer monthNum;
    private Integer year;
}
