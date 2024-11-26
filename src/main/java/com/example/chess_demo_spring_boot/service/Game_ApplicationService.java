package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessColor;
import com.example.chess_demo_spring_boot.domain.Game_Application;

import java.sql.Time;
import java.util.List;

public interface Game_ApplicationService {
    List<Game_Application> findByColor(ChessColor color);
    List<Game_Application> findByGame_Time(Time time);
    List<Game_Application> findByBusy(boolean busy);
    List<Game_Application> findAll();
}
