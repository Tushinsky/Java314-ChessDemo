package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.repository.ChessPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChessPartyServiceImpl implements ChessPartyService{
    private final ChessPartyRepository repository;

    @Override
    public List<ChessParty> getAllByChessMan(ChessMan chessMan) {
        return repository.findAllByChessMan(chessMan);
    }

    @Override
    public List<ChessParty> findAllByChessManAndFinished(ChessMan chessMan, boolean isFinished) {
        return repository.findAllByChessManAndFinished(chessMan, isFinished);
    }
}
