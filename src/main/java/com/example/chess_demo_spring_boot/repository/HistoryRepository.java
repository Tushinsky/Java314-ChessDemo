package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByResult(String result);
    History findByChessParty(ChessParty chessParty);
    List<History> findAllByChessMan(ChessMan chessMan);
    Optional<History> findById(Long id);
    List<History> findAllByChessParty(List<ChessParty> parties);
}
