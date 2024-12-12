package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;

public interface GameApplicationRepository extends JpaRepository<GameApplication, Long> {

    List<GameApplication> findAllByColor(String color);
    List<GameApplication> findAllByGameTime(Time time);

    List<GameApplication> findAllByBusy(boolean busy);

    GameApplication findByChessMan(ChessMan chessMan);
}
