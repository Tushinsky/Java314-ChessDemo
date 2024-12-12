package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.GameApplication;
import lombok.*;

import java.sql.Time;

/**
 * DTO for{@link GameApplication}
 */
@Getter
@Setter
@Builder
public class GameApplicationDto {
    Long id;
    String nic;
    String color;
    Time gameTime;
    boolean busy;
}
