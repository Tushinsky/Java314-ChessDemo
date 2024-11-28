package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column(name = "partydate")
    private Date partyDate;

    @Column(name = "result")
    private String result;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChessMan.class)
    @JoinColumn(name = "idChessman", referencedColumnName = "id")
    private ChessMan chessMan;
}
