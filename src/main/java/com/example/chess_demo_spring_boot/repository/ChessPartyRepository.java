package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChessPartyRepository extends JpaRepository<ChessParty, Long> {
    List<ChessParty> findAllByChessMan(ChessMan chessMan);
    List<ChessParty> findAllByChessManAndIsFinished(ChessMan chessMan, boolean isFinished);
}
