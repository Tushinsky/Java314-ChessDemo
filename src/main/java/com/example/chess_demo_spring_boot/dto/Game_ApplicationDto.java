package com.example.chess_demo_spring_boot.dto;

import lombok.*;

import java.sql.Time;

/**
 * DTO for{@link com.example.chess_demo_spring_boot.domain.Game_Application}
 */
@Getter
@Setter
@Builder
public class Game_ApplicationDto {
    Long id;
    String nic;
    String color;
    Time gameTime;
    boolean busy;
}
