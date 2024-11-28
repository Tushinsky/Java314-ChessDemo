package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Chess_Color;
import com.example.chess_demo_spring_boot.domain.Game_Application;

import java.sql.Time;
import java.util.List;

public interface Game_ApplicationService {
    List<Game_Application> getAllByColor(Chess_Color color);
    List<Game_Application> getAllByGameTime(Time time);
    List<Game_Application> getAllByBusy(boolean busy);
    List<Game_Application> getAll();
}
