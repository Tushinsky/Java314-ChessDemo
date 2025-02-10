package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.Opponent;

import java.util.List;
import java.util.Optional;

public interface OpponentService {
    List<Opponent> getAllByChessMan(ChessMan chessMan);
    Opponent getByChessParty(ChessParty chessParty);
}
