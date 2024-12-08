package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.History;

import java.util.List;

public interface HistoryService {
    List<History> getAllHistories();
    List<History> getAllByResult(String result);
    History getByChessParty(ChessParty chessParty);
    List<History> getAllByChessMan(ChessMan chessMan);
}
