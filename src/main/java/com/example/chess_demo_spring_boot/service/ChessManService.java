package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Opponent;
import com.example.chess_demo_spring_boot.dto.HistoryDto;

import java.util.List;
import java.util.Optional;

public interface ChessManService {
    ChessMan getByName(String name);
    ChessMan getByEmail(String email);
    List<ChessMan> getByEmailLike(String email);
    ChessMan getByNic(String nic);
    List<ChessMan> getAllByNicLike(String nicName);
    Optional<ChessMan> getBy_Id(Long id);
    List<ChessMan> getAllByName(String name);
    List<ChessMan> getAllByCity(String city);
    List<ChessMan> getAllByCountry(String country);
    List<ChessMan> getAllByState(String state);
    void save(ChessMan chessMan);
    void updateChessMan(ChessMan chessMan);
    void removeChessMan(Long id);

    List<ChessMan> getAll();

    List<HistoryDto> getAllHistoryByChessMan(ChessMan chessMan, boolean isFinished);

}
