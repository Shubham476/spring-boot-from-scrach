package com.example.springSecurityApp.dto;

import lombok.Data;

@Data
public class SignUpDTO {

    private String email;
    private String password;
    private String name;
}
