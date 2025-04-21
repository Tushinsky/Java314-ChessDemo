package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessParty;
import com.example.chess_demo_spring_boot.domain.History;
import com.example.chess_demo_spring_boot.domain.Opponent;
import com.example.chess_demo_spring_boot.dto.HistoryDto;
import com.example.chess_demo_spring_boot.repository.ChessManRepository;
import com.example.chess_demo_spring_boot.repository.ChessPartyRepository;
import com.example.chess_demo_spring_boot.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChessManServiceImpl implements ChessManService {

    private final ChessManRepository repository;
    private final HistoryRepository historyRepository;
    private final ChessPartyRepository partyRepository;
    private final OpponentService opponentService;

    @Override
    @Transactional
    public ChessMan getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public ChessMan getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    @Transactional
    public List<ChessMan> getByEmailLike(String email) {
        return repository.findByEmailLike(email);
    }

    @Override
    @Transactional
    public ChessMan getByNic(String nic) {
        return repository.getByNic(nic);
    }

    @Override
    @Transactional
    public List<ChessMan> getAllByNicLike(String nicName) {
        return repository.findAllByNicLike(nicName);
    }

    @Override
    @Transactional
    public Optional<ChessMan> getBy_Id(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public List<ChessMan> getAllByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    @Transactional
    public List<ChessMan> getAllByCity(String city) {
        return repository.findAllByCity(city);
    }

    @Override
    @Transactional
    public List<ChessMan> getAllByCountry(String country) {
        return repository.findAllByCountry(country);
    }

    @Override
    @Transactional
    public List<ChessMan> getAllByState(String state) {
        return repository.findAllByState(state);
    }

    @Override
    @Transactional
    public void save(ChessMan chessMan) {
        repository.save(chessMan);
    }

    @Override
    @Transactional
    public void updateChessMan(ChessMan chessMan) {
        repository.save(chessMan);
    }

    @Override
    @Transactional
    public void removeChessMan(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<ChessMan> getAll() {
        List<ChessMan> chessManList = repository.findAll();
        if(chessManList.isEmpty()) {
            return null;
        }
        return chessManList;
    }

    @Override
    @Transactional
    public List<HistoryDto> getAllHistoryByChessMan(ChessMan chessMan, boolean isFinished) {
        // получаем список партий, созданных пользователем
        List<ChessParty> partyList = partyRepository.findAllByChessManAndIsFinished(chessMan, isFinished);
        List<HistoryDto> list = new ArrayList<>();
        for (ChessParty party : partyList) {
            History history = historyRepository.findByChessParty(party);
            // проверяем, что шахматная партия была создана и были сделаны ходы
            if(history != null) {
                Opponent opponent = opponentService.getByChessParty(party);
                HistoryDto historyDto = getHistoryDto(history);
                historyDto.setOpponent(opponent.getChessMan().getNic());
                list.add(historyDto);
            }
        }
        // получаем список, в котором пользователь выступает как оппонент
        List<Opponent> opponents = opponentService.getAllByChessMan(chessMan);
        for(Opponent opponent : opponents) {
            ChessParty party = opponent.getChessParty();
            // проверяем соответствие шахматной партии условию
            if(party.isFinished() == isFinished) {
                History history = historyRepository.findByChessParty(party);
                // проверяем, что шахматная партия была создана и были сделаны ходы
                if (history != null) {
                    HistoryDto historyDto = getHistoryDto(history);
                    historyDto.setOpponent(history.getChessParty().getChessMan().getNic());
                    list.add(historyDto);
                }
            }
        }
        return list;
    }

    private HistoryDto getHistoryDto(History history) {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(history.getId());
        historyDto.setPartyDate(history.getChessParty().getPartydate().toString());
        historyDto.setResult(history.getResult());
        return historyDto;
    }
}
