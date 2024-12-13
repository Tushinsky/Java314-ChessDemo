package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findAllByChessMan(ChessMan chessMan);
    List<Challenge> findAllByOpponent(ChessMan opponent);
}
