package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.Opponent;
import com.example.chess_demo_spring_boot.repository.OpponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OpponentServiceImpl implements OpponentService{
    private final OpponentRepository repository;
    @Override
    public List<Opponent> getAllByChessMan(ChessMan chessMan) {
        return repository.findAllByChessMan(chessMan);
    }

    @Override
    public Opponent getByChessParty(ChessParty chessParty) {
        if(repository.findByChessParty(chessParty).isPresent()) {
            return repository.findByChessParty(chessParty).get();
        }
        return null;
    }
}
