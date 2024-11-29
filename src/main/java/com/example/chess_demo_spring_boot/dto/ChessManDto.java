package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import lombok.*;

/**
 * DTO for {@link ChessMan}
 */
@Data
public class ChessManDto {
    String name;
    String nickName;
    String password;

    @Override
    public String toString() {
        return "ChessManDto{" +
                "name='" + name + '\'' +
                ", nick='" + nickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
//    public static ChessManDto from(ChessMan chessMan) {
//        return new ChessManDto(chessMan.getNicName(), chessMan.getPassword());
//    }
//
//    public static List<ChessManDto> from(List<ChessMan> chessMEN) {
//        return chessMEN.stream().map(ChessManDto::from).collect(Collectors.toList());
//    }
}
