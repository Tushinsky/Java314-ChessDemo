package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessColor;
import com.example.chess_demo_spring_boot.domain.Game_Application;
//import com.example.chess_demo_spring_boot.repository.Game_ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
@Service
@AllArgsConstructor
public class Game_ApplicationServiceImpl implements Game_ApplicationService{
//    private final Game_ApplicationRepository gameApplicationRepository;
    @Override
    public List<Game_Application> findByColor(ChessColor color) {
        return null;
    }

    @Override
    public List<Game_Application> findByGame_Time(Time time) {
        return null;
    }

    @Override
    public List<Game_Application> findByBusy(boolean busy) {
        return null;
    }

    @Override
    public List<Game_Application> findAll() {
        return null;
    }
}
