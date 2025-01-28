package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;

import java.util.List;

public interface ChessPartyService {
    List<ChessParty> getAllByChessMan(ChessMan chessMan);
    List<ChessParty> findAllByChessManAndFinished(ChessMan chessMan, boolean isFinished);
}
