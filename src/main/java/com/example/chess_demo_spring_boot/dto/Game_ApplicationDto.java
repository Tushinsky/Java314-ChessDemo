package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.Chess_Color;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import lombok.Value;

import java.sql.Time;
import java.util.List;

@Value
public class Game_ApplicationDto {
    Long id;
    List<ChessMan> chessManList;
    Chess_Color color;
    Time gameTime;
    boolean busy;


}
