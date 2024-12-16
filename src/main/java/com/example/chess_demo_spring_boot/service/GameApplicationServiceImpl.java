package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessColor;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import com.example.chess_demo_spring_boot.dto.GameApplicationDto;
import com.example.chess_demo_spring_boot.repository.GameApplicationRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GameApplicationServiceImpl implements GameApplicationService {
    private final GameApplicationRepository gameApplicationRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional
    public List<GameApplication> getAllByColor(ChessColor color) {
        return gameApplicationRepository.findAllByColor(color.toString());
    }

    @Override
    @Transactional
    public List<GameApplication> getAllByGameTime(Time time) {
        return gameApplicationRepository.findAllByGameTime(time);
    }

    @Override
    @Transactional
    public List<GameApplication> getAllByBusy(boolean busy) {
        return gameApplicationRepository.findAllByBusy(busy);
    }

    @Override
    @Transactional
    public List<GameApplication> getAll() {
        return gameApplicationRepository.findAll();
    }

//    @Override
//    @Transactional
//    public List<GameApplicationDto> getAllByChessmanIsNot(ChessMan chessMan) {
//        List<GameApplication> applicationList = gameApplicationRepository.findAll();
//        logger.info("applicationList size=" + applicationList.size());
//        List<GameApplicationDto> applicationDtos = new ArrayList<>();
//        if(!applicationList.isEmpty()) {
//            applicationList.stream().filter(item -> !item.getChessMan().equals(chessMan)).forEach(item -> {
//                GameApplicationDto dto = GameApplicationDto.builder().id(item.getId())
//                        .nic(item.getChessMan().getNic())
//                        .color(item.getColor())
//                        .gameTime(item.getGameTime())
//                        .busy(item.isBusy())
//                                .build();
//                applicationDtos.add(dto);
//            });
//            return applicationDtos;
//        }
//        return null;
//    }

    @Override
    @Transactional
    public GameApplication getByChessMan(ChessMan chessMan) {
        return gameApplicationRepository.findByChessMan(chessMan);
    }

    @Override
    @Transactional
    public GameApplication getById(Long id) {
        return gameApplicationRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void save(GameApplication gameApplication) {
        gameApplicationRepository.save(gameApplication);
    }

    @Override
    @Transactional
    public List<GameApplication> getAllByChessmanIsNot(ChessMan chessMan) {
        List<GameApplication> applicationList = gameApplicationRepository.findAll();
        logger.info("applicationList size=" + applicationList.size());
        if(!applicationList.isEmpty()) {
            return applicationList.stream().filter(item -> !item.getChessMan().equals(chessMan)).toList();
        }
        return null;
    }

}
