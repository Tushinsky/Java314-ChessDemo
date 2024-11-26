package com.example.chess_demo_spring_boot.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String name;
    private String email;
    private String nicName;
    private String password;
    private String city;
    private String country;
    private String role;
    private String state;
}
