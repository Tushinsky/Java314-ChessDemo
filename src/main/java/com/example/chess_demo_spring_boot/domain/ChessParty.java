package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chess_party")
@Builder
public class ChessParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partydate")
    private Date partydate;

    @ManyToOne()
    @JoinColumn(name = "idchessman", referencedColumnName = "id")
    private ChessMan chessMan;

    @JoinColumn(name = "idopponent", referencedColumnName = "id")
    private ChessMan opponent;

    @Column(name = "finished")
    private boolean isFinished;
}
