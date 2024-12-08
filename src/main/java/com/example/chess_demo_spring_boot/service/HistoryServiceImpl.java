package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.History;
import com.example.chess_demo_spring_boot.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository repository;

    @Override
    @Transactional
    public List<History> getAllHistories() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public List<History> getAllByResult(String result) {
        return repository.findAllByResult(result);
    }

    @Override
    @Transactional
    public History getByChessParty(ChessParty chessParty) {
        return repository.findByChessParty(chessParty);
    }

    @Override
    @Transactional
    public List<History> getAllByChessMan(ChessMan chessMan) {
        return repository.findAllByChessMan(chessMan);
    }
}
