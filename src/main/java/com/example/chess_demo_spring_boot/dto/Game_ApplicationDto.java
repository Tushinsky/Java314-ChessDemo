package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.ChessColor;
import com.example.chess_demo_spring_boot.domain.Game_Application;
import lombok.Value;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Game_ApplicationDto {
    Long id;
    String chessMan;
    ChessColor color;
    Time gameTime;
    boolean busy;

    public static Game_ApplicationDto from(Game_Application application) {
        return new Game_ApplicationDto(application.getId(), application.getChessMan().getNic(),
                application.getColor(), application.getGameTime(), application.isBusy());
    }
    public static List<Game_ApplicationDto> from(List<Game_Application> applications) {
        return applications.stream().map(Game_ApplicationDto::from).collect(Collectors.toList());
    }
}
