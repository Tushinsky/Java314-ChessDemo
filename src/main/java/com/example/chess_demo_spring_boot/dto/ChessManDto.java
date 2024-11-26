package com.example.chess_demo_spring_boot.dto;

import lombok.Data;

@Data
public class ChessManDto {
//    Long id;
    String nicName;
    String password;

    @Override
    public String toString() {
        return "ChessManDto{" +
                "nicName='" + nicName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
