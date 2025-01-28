package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "result")
    private String result;

    @ManyToOne()
    @JoinColumn(name = "idparty")
    private ChessParty chessParty;
}
