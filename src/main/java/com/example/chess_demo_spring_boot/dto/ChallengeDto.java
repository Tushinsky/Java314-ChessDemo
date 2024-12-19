package com.example.chess_demo_spring_boot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChallengeDto {
    private Long id;
    private String chessManName;
    private String opponentName;
    private boolean takeIt;

    @Override
    public String toString() {
        return "ChallengeDto{" +
                "id=" + id +
                ", chessManName='" + chessManName + '\'' +
                ", opponentName='" + opponentName + '\'' +
                ", takeIt=" + takeIt +
                '}';
    }
}
