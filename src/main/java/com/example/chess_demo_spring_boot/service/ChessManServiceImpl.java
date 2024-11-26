package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.repository.ChessManRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChessManServiceImpl implements ChessManService {

    private final ChessManRepository repository;
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
}
