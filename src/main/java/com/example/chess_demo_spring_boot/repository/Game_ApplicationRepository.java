package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Chess_Color;
import com.example.chess_demo_spring_boot.domain.Game_Application;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;

public interface Game_ApplicationRepository extends JpaRepository<Game_Application, Long> {

    List<Game_Application> findAllByColor(String color);
    List<Game_Application> findAllByGameTime(Time time);

    List<Game_Application> findAllByBusy(boolean busy);

    Game_Application findByChessMan(ChessMan chessMan);
}
