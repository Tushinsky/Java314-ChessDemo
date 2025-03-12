package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.Opponent;
import com.example.chess_demo_spring_boot.repository.ChessPartyRepository;
import com.example.chess_demo_spring_boot.repository.OpponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChessPartyServiceImpl implements ChessPartyService{
    private final ChessPartyRepository repository;
    private final OpponentRepository opponentRepository;
    @Override
    @Transactional
    public List<ChessParty> getAllByChessMan(ChessMan chessMan) {
        return repository.findAllByChessMan(chessMan);
    }

    @Override
    @Transactional
    public List<ChessParty> getAllByChessManAndIsFinished(ChessMan chessMan, boolean isFinished) {
        return repository.findAllByChessManAndIsFinished(chessMan, isFinished);
    }

    @Override
    @Transactional
    public void addParty(ChessMan chessMan, ChessMan opponent) {
        // создаём новую партию и сохраняем её в базе
        ChessParty party = ChessParty.builder()
                .id(0L)
                .chessMan(chessMan)
                .isFinished(false)
                .partydate(Date.valueOf(LocalDate.now()))
                .build();

        ChessParty chessParty = repository.save(party);
        // после создания новой партии добавляем оппонента по этой партии в таблицу
        Opponent opponent1 = Opponent.builder()
                .id(0L)
                .chessMan(opponent)
                .chessParty(chessParty)
                .build();
        opponentRepository.save(opponent1);
    }

}
