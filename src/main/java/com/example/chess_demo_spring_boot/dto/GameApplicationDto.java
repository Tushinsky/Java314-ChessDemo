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

    @Override
    public String toString() {
        return "GameApplicationDto{" +
                "nic='" + nic + '\'' +
                ", color='" + color + '\'' +
                ", gameTime=" + gameTime +
                ", busy=" + busy +
                '}';
    }
}
