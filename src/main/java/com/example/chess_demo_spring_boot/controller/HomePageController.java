package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.History;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final ChessManService chessManService;
    private final HistoryService historyService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home_page/{id}")
    public String getHomePage(@PathVariable("id") String id, Model model) {

        // получаем текущего пользователя
        Long idChessman = Long.valueOf(id);
        Optional<ChessMan> chessMan = chessManService.getBy_Id(idChessman);
        ChessMan man = chessMan.get();
        // передаём полученные данные в модель страницы для вывода
        List<History> historyList = historyService.getAllByChessMan(man);
        logger.info("historyList: size=" + historyList.size());
        model.addAttribute("name", man.getName());
        if(historyList.size() > 0) {
            model.addAttribute("historyList", historyList);
        }
        return "home_page";
    }
}
