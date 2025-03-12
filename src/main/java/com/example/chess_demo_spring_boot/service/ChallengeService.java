package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.dto.ChallengeDto;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    List<ChallengeDto> getAllByChessMan(ChessMan chessMan);
    List<ChallengeDto> getAllByOpponent(ChessMan opponent);
    void saveChallenge(Challenge challenge);
    void removeChallenge(Long id);
    void removeChallenge(Challenge challenge);
    Optional<Challenge> getBy_Id(Long id);
    Challenge getByChessManAndOpponent(ChessMan chessMan, ChessMan opponent);
}
