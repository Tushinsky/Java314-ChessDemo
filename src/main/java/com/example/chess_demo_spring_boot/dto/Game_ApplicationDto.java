package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.ChessColor;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Game_Application;
import lombok.Value;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Game_ApplicationDto {
    Long id;
    List<ChessMan> chessManList;
    ChessColor color;
    Time gameTime;
    boolean busy;


}
