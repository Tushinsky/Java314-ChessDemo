package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.Opponent;
import com.example.chess_demo_spring_boot.repository.OpponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OpponentServiceImpl implements OpponentService{
    private final OpponentRepository repository;
    @Override
    public List<Opponent> findAllByChessman(ChessMan chessMan) {
        return repository.findAllByChessman(chessMan);
    }

    @Override
    public Optional<Opponent> findByChessParty(ChessParty chessParty) {

        return repository.findByChessParty(chessParty);
    }
}
