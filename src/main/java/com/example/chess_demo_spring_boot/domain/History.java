package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opponent")
    private String opponent;

    @Column(name = "result")
    private String result;

    @ManyToOne()
    @JoinColumn(name = "idchessman")
    private ChessMan chessMan;

    @OneToOne()
    @JoinColumn(name = "idparty")
    private ChessParty chessParty;
}
