package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Chess_Color;
import com.example.chess_demo_spring_boot.domain.Game_Application;
import com.example.chess_demo_spring_boot.repository.Game_ApplicationRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class Game_ApplicationServiceImpl implements Game_ApplicationService{
    private final Game_ApplicationRepository gameApplicationRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional
    public List<Game_Application> getAllByColor(Chess_Color color) {
        return gameApplicationRepository.findAllByColor(color.toString());
    }

    @Override
    @Transactional
    public List<Game_Application> getAllByGameTime(Time time) {
        return gameApplicationRepository.findAllByGameTime(time);
    }

    @Override
    @Transactional
    public List<Game_Application> getAllByBusy(boolean busy) {
        return gameApplicationRepository.findAllByBusy(busy);
    }

    @Override
    @Transactional
    public List<Game_Application> getAll() {
        return gameApplicationRepository.findAll();
    }

    @Override
    @Transactional
    public List<Game_Application> getAllByChessmanIsNot(ChessMan chessMan) {
        List<Game_Application> applicationList = gameApplicationRepository.findAll();
        logger.info("applicationList size=" + applicationList.size());
        if(!applicationList.isEmpty()) {
            return applicationList.stream().filter(item ->
                            !Objects.equals(item.getChessMan(), chessMan)).toList();
        }
        return null;
    }

    @Override
    @Transactional
    public Game_Application getByChessMan(ChessMan chessMan) {
        return gameApplicationRepository.findByChessMan(chessMan);
    }

    @Override
    @Transactional
    public void save(Game_Application gameApplication) {
        gameApplicationRepository.save(gameApplication);
    }
}
