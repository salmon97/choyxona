package com.example.choyxona.payload;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class ReqRegister {
    private UUID id;
    private String email;
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$", message = "Uzunligi 6-10 oraligida bolishi kerak")
    private String password;
    private String prePassword;
}
