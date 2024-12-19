package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.dto.ChallengeDto;
import com.example.chess_demo_spring_boot.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository challengeRepository;
    @Override
    public List<ChallengeDto> getAllByChessMan(ChessMan chessMan) {
        List<Challenge> challenges = challengeRepository.findAllByChessMan(chessMan);
        List<ChallengeDto> dtos = new ArrayList<>();
        challenges.forEach(item -> {
            ChallengeDto dto = ChallengeDto.builder()
                    .id(item.getId())
                    .chessManName(item.getChessMan().getNic())
                    .opponentName(item.getOpponent().getNic())
                    .takeIt(item.isTakeIt())
                    .build();
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public List<ChallengeDto> getAllByOpponent(ChessMan opponent) {
        List<Challenge> challenges = challengeRepository.findAllByOpponent(opponent);
        List<ChallengeDto> dtos = new ArrayList<>();
        challenges.forEach(item -> {
            ChallengeDto dto = ChallengeDto.builder()
                    .id(item.getId())
                    .chessManName(item.getChessMan().getNic())
                    .opponentName(item.getOpponent().getNic())
                    .takeIt(item.isTakeIt())
                    .build();
            dtos.add(dto);
        });
        return dtos;
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

    @Override
    public Optional<Challenge> getBy_Id(Long id) {
        return challengeRepository.findById(id);
    }
}
