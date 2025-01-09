package com.example.chess_demo_spring_boot.dto;

import lombok.Data;

@Data
public class HistoryDto {
    private Long id;
    private String chessman;
    private String partyDate;
    private String result;

}
