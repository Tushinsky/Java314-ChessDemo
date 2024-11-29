package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;

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
    @OneToMany(fetch = FetchType.LAZY, targetEntity = ChessMan.class)
    @JoinColumn(name = "idChessman", referencedColumnName = "id")
    private List<ChessMan> chessManList;
}
