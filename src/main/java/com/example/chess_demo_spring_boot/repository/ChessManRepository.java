package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChessManRepository extends JpaRepository<ChessMan, Long> {
    ChessMan getByEmail(String email);
    List<ChessMan> findByEmailLike(String email);
    ChessMan getByNic(String nic);
    List<ChessMan> findAllByNicLike(String nicLike);
//    ChessMan getById(Long id);

    List<ChessMan> findAllByName(String name);
    List<ChessMan> findAllByCity(String city);
    List<ChessMan> findAllByCountry(String country);
    List<ChessMan> findAllByState(String state);
}
