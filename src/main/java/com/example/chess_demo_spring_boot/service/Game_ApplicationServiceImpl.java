package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Chess_Color;
import com.example.chess_demo_spring_boot.domain.Game_Application;
//import com.example.chess_demo_spring_boot.repository.Game_ApplicationRepository;
import com.example.chess_demo_spring_boot.repository.Game_ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
@Service
@AllArgsConstructor
public class Game_ApplicationServiceImpl implements Game_ApplicationService{
    private final Game_ApplicationRepository gameApplicationRepository;
    @Override
    public List<Game_Application> getAllByColor(Chess_Color color) {
        return gameApplicationRepository.findAllByColor(color.toString());
    }

    @Override
    public List<Game_Application> getAllByGameTime(Time time) {
        return gameApplicationRepository.findAllByGameTime(time);
    }

    @Override
    public List<Game_Application> getAllByBusy(boolean busy) {
        return gameApplicationRepository.findAllByBusy(busy);
    }

    @Override
    public List<Game_Application> getAll() {
        return gameApplicationRepository.findAll();
    }
}
