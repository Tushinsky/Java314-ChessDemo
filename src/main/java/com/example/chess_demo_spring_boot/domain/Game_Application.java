package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_application")
@Builder
public class Game_Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ChessColor color;
    private Time gameTime;
    private boolean busy;
    private ChessMan chessMan;
}
