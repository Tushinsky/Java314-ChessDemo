package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.GameProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameProgressRepository extends JpaRepository<GameProgress, Long> {
    List<GameProgress> findAllByChessParty(ChessParty chessParty);
}
