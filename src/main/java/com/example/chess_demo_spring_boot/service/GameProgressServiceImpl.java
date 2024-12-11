package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.GameProgress;
import com.example.chess_demo_spring_boot.repository.GameProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GameProgressServiceImpl implements GameProgressService{
    private final GameProgressRepository repository;
    @Override
    public List<GameProgress> getAllByChessParty(ChessParty chessParty) {
        return repository.findAllByChessParty(chessParty);
    }
}
