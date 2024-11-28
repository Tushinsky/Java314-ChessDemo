package com.example.chess_demo_spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;
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
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String name;

    @Column(name = "email", unique = true)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String email;

    @Column(name = "nic", unique = true)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String nic;

    @Column(name = "password")
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String password;

    @Column(name = "city")
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String city;

    @Column(name = "country")
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String country;

    @Column(name = "role")
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String role;

    @Column(name = "state")
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String state;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
    private Game_Application gameApplication;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<History> histories;

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
