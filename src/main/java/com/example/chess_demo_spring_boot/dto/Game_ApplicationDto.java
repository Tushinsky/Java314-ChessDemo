package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.Chess_Color;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import lombok.Value;

import java.sql.Time;
import java.util.List;

/**
 * DTO for{@link com.example.chess_demo_spring_boot.domain.Game_Application}
 */
@Value
public class Game_ApplicationDto {
    Long id;
    ChessMan chessMan;
    Chess_Color color;
    Time gameTime;
    boolean busy;


}
