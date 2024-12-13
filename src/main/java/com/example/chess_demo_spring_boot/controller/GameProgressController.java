package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.GameProgress;
import com.example.chess_demo_spring_boot.domain.History;
import com.example.chess_demo_spring_boot.dto.GameProgressDto;
import com.example.chess_demo_spring_boot.service.GameProgressService;
import com.example.chess_demo_spring_boot.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GameProgressController {
    private final GameProgressService progressService;
    private final HistoryService historyService;

    /**
     * Отрисовка страницы с ходом выбранной шахматной партии
     * @param id код записи из таблицы История
     * @param model модель страницы
     * @return страница с данными
     */
    @RequestMapping(value="/progress/{id}")
    public String getProgress(@PathVariable("id") String id, Model model) {
        Long idHistory = Long.valueOf(id);
        Optional<History> optionalHistory = historyService.getById(idHistory);
        if(optionalHistory.isPresent()) {
            History history = optionalHistory.get();
            List<GameProgress> progressList = progressService.getAllByChessParty(history.getChessParty());
            List<GameProgressDto> dtos = new ArrayList<>();
            progressList.forEach(item -> {
                GameProgressDto dto = GameProgressDto.builder()
                        .chessManName(item.getChessMan().getNic())
                        .moving(item.getMoving())
                        .build();
                dtos.add(dto);
            });
            model.addAttribute("partyDate", history.getChessParty().getPartydate());
            model.addAttribute("progressList", dtos);
        }
        return "progress";
    }
}
