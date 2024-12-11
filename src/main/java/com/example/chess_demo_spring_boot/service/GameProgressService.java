package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.GameProgress;

import java.util.List;

public interface GameProgressService {
    List<GameProgress> getAllByChessParty(ChessParty chessParty);
}
