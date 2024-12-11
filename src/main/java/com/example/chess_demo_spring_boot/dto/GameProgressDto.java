package com.example.chess_demo_spring_boot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameProgressDto {
    private String chessManName;
    private String moving;
}
