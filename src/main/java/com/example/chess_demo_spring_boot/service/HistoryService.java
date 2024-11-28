package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.History;

import java.util.Date;
import java.util.List;

public interface HistoryService {
    List<History> getAllHistories();
    List<History> getAllByResult(String result);
    List<History> getAllByDate(Date date);
}
