package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.Opponent;

import java.util.List;
import java.util.Optional;

public interface ChessPartyService {
    List<ChessParty> getAllByChessMan(ChessMan chessMan);
    List<ChessParty> getAllByChessManAndIsFinished(ChessMan chessMan, boolean isFinished);
    void addParty(ChessMan chessMan, ChessMan opponent);
    Optional<ChessParty> getBy_Id(Long id);
}
