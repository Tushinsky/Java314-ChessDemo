package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository challengeRepository;
    @Override
    public List<Challenge> getAllByChessMan(ChessMan chessMan) {
        return challengeRepository.findAllByChessMan(chessMan);
    }

    @Override
    public List<Challenge> findAllByOpponent(ChessMan opponent) {
        return challengeRepository.findAllByOpponent(opponent);
    }

    @Override
    public void saveChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @Override
    public void removeChallenge(Long id) {
        challengeRepository.deleteById(id);
    }

    @Override
    public void removeChallenge(Challenge challenge) {
        challengeRepository.delete(challenge);
    }
}
