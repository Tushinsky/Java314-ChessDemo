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
    @Column(name = "color")
    private String color;
    @Column(name = "gametime")
    private Time gameTime;
    @Column(name = "busy")
    private boolean busy;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ChessMan.class)
    @JoinColumn(name = "idchessman", referencedColumnName = "id")
    private ChessMan chessMan;

    @Override
    public String toString() {
        return "Game_Application{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", gameTime=" + gameTime +
                ", busy=" + busy +
                ", chessMan=" + chessMan +
                '}';
    }
}
