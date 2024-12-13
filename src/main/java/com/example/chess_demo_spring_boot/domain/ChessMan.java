package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chessman")
@Builder
public class ChessMan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nic", unique = true)
    private String nic;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "role")
    private String role;

    @Column(name = "state")
    private String state;

//    @OneToOne(targetEntity = GameApplication.class)
//    @JoinColumn(name = "id", referencedColumnName = "idchessman")
//    private GameApplication gameApplication;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<History> histories;

//    private String activationUUID;
//    public boolean isAdmin() {
//        return role.name().equalsIgnoreCase(ADMIN.name());
//    }

    @Override
    public String toString() {
        return "ChessMan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nicName='" + nic + '\'' +
                ", city=" + city +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMan chessMan = (ChessMan) o;
        return Objects.equals(id, chessMan.id) && Objects.equals(name, chessMan.name)
                && Objects.equals(email, chessMan.email) && Objects.equals(nic, chessMan.nic)
                && Objects.equals(password, chessMan.password) && Objects.equals(city, chessMan.city)
                && Objects.equals(country, chessMan.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, nic, password, city, country);
    }
}
