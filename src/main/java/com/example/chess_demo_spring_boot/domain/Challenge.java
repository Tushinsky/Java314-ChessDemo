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
    @OneToOne
    @JoinColumn(name = "idchessman")
    private ChessMan chessMan;
    @OneToOne
    @JoinColumn(name = "idopponent")
    private ChessMan opponent;

    @Column(name = "takeit")
    private boolean takeIt;

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", chessMan=" + chessMan +
                ", opponent=" + opponent +
                ", takeIt=" + takeIt +
                '}';
    }
}
