package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;

import java.util.List;

public interface ChallengeService {
    List<Challenge> getAllByChessMan(ChessMan chessMan);
    List<Challenge> findAllByOpponent(ChessMan opponent);
    void saveChallenge(Challenge challenge);
    void removeChallenge(Long id);
    void removeChallenge(Challenge challenge);
}
