package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.History;

import java.util.List;
import java.util.Optional;

public interface HistoryService {
    List<History> getAllHistories();
    List<History> getAllByResult(String result);
    History getByChessParty(ChessParty chessParty);
    List<History> getAllByChessMan(ChessMan chessMan);
    Optional<History> getById(Long id);
    void addHistory(ChessParty party);
    void updateHistory(ChessParty chessParty, String result);
}
