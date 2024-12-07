package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chess_party")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChessParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partydate")
    private Date partydate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<History> histories;
}
