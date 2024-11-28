package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByResult(String result);
    List<History> findByPartyDate(Date date);
}
