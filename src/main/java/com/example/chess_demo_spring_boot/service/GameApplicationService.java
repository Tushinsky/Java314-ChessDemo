package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessColor;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import com.example.chess_demo_spring_boot.dto.GameApplicationDto;

import java.sql.Time;
import java.util.List;

public interface GameApplicationService {
    List<GameApplication> getAllByColor(ChessColor color);
    List<GameApplication> getAllByGameTime(Time time);
    List<GameApplication> getAllByBusy(boolean busy);
    List<GameApplication> getAll();
//    List<GameApplicationDto> getAllByChessmanIsNot(ChessMan chessMan);
    GameApplication getByChessMan(ChessMan chessMan);
    GameApplication getById(Long id);
    void save(GameApplication gameApplication);
    List<GameApplication> getAllByChessmanIsNot(ChessMan chessMan);
}
