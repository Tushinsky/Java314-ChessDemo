package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "challenge")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ChessMan.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "idchessman", referencedColumnName = "id")
    private ChessMan chessMan;

    @OneToOne
    @JoinColumn(name = "idopponent")
    private ChessMan opponent;

    @Column(name = "takeit")
    private boolean takeIt;
}
