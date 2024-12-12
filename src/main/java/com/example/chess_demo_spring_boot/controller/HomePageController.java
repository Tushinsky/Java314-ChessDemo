package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import com.example.chess_demo_spring_boot.dto.GameApplicationDto;
import com.example.chess_demo_spring_boot.dto.HistoryDto;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.GameApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final ChessManService chessManService;
    private final GameApplicationService gameApplicationService;
    private ChessMan chessMan;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home/{id}")
    public String getHomePage(@PathVariable("id") String id, Model model) {

        // получаем текущего пользователя
        Long idChessman = Long.valueOf(id);
        Optional<ChessMan> chessManServiceById = chessManService.getBy_Id(idChessman);
        if(chessManServiceById.isPresent()) {
            chessMan = chessManServiceById.get();
            // передаём полученные данные в модель страницы для вывода
            List<HistoryDto> historyList = chessManService.getAllHistoryByChessMan(chessMan);
            logger.info("historyList: size=" + historyList.size());

            model.addAttribute("chessman", chessMan);
            if (historyList.size() > 0) {
                model.addAttribute("historyList", historyList);

            }

            GameApplication game_application = gameApplicationService.getByChessMan(chessMan);
            if(game_application != null) {
                logger.info(game_application.toString());
                model.addAttribute("gameApp", game_application);
            }


            List<GameApplicationDto> appList = gameApplicationService.getAllByChessmanIsNot(chessMan);
            if (appList != null) {
//                logger.info("appList: size=" + appList.size());
//                appList.forEach(item -> logger.info(item.getChessMan().toString()));
                model.addAttribute("appList", appList);
            }

            return "home";
        }
        return "redirect:/start";
    }

    /**
     * Добавляет запись в таблицу приглашений к игре других оппонентов
     * @param id код записи из таблицы заявок на игру
     * @return возвращает на домашнюю страницу
     */
    @PostMapping("home/challenge/{id}")
    public String addChallenge(@PathVariable("id") Long id) {
        return "home";
    }
}
