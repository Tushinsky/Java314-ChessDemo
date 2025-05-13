package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.client_server.JClient;
import com.example.chess_demo_spring_boot.domain.GameProgress;
import com.example.chess_demo_spring_boot.domain.History;
import com.example.chess_demo_spring_boot.dto.GameProgressDto;
import com.example.chess_demo_spring_boot.service.GameProgressService;
import com.example.chess_demo_spring_boot.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GameProgressController {
    private final GameProgressService progressService;
    private final HistoryService historyService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JClient client = new JClient();
    /**
     * Отрисовка страницы с ходом выбранной шахматной партии
     * @param id код записи из таблицы История
     * @param model модель страницы
     * @return страница с данными
     */
    @RequestMapping(value="/progress/{historyId:\\d+}/{chessmanId:\\d+}")
    public String getProgress(@PathVariable("historyId") Long id, @PathVariable ("chessmanId") Long chessmanId,
                              Model model) {
//        Long idHistory = Long.valueOf(id);
        Optional<History> optionalHistory = historyService.getById(id);
        if(optionalHistory.isPresent()) {
            History history = optionalHistory.get();
            List<GameProgress> progressList = progressService.getAllByChessParty(history.getChessParty());
            List<GameProgressDto> dtos = new ArrayList<>();
            // проверяем наличие ходов в шахматной партии
            if(!progressList.isEmpty()) {
                progressList.forEach(item -> {
                    GameProgressDto dto = GameProgressDto.builder()
                            .chessManName(item.getChessMan().getNic())
                            .moving(item.getMoving())
                            .build();
                    dtos.add(dto);
                });
            }
            model.addAttribute("partyDate", history.getChessParty().getPartydate());
            model.addAttribute("progressList", dtos);
            client.start();
        }
        return "progress";
    }
}
