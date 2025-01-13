package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.Opponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpponentRepository extends JpaRepository<Opponent, Long> {
    List<Opponent> findAllByChessman(ChessMan chessMan);
    Optional<Opponent> findByChessParty(ChessParty chessParty);
}
