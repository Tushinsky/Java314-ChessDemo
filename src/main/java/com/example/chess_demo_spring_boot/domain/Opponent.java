package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opponent")
@Builder
public class Opponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idchessman", referencedColumnName = "id")
    private ChessMan chessMan;

    @ManyToOne
    @JoinColumn(name = "idparty", referencedColumnName = "id")
    private ChessParty chessParty;

}
