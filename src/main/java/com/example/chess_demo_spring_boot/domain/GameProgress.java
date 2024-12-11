package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_progress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ChessParty.class)
    @JoinColumn(name = "idparty", referencedColumnName = "id")
    private ChessParty chessParty;

    @ManyToOne(targetEntity = ChessMan.class)
    @JoinColumn(name = "idchessman", referencedColumnName = "id")
    private ChessMan chessMan;

    @Column(name = "moving")
    private String moving;
}
