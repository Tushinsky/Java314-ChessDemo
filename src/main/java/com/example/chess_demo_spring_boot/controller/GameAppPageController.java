package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Chess_Color;
import com.example.chess_demo_spring_boot.domain.Game_Application;
import com.example.chess_demo_spring_boot.dto.Game_ApplicationDto;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.Game_ApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Time;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GameAppPageController {
    private final Game_ApplicationService gameApplicationService;
    private final ChessManService chessManService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Long idChessman;
    private ChessMan chessMan;
    /**
     * Отображает страницу с данными для изменения/подачи заявки на участие в игре
     * @param id код пользователя
     * @param model модель страницы
     * @return визуальное представление страницы в браузере
     */
    @RequestMapping("/gameApp_page/{id}")
    public String changeApplication(@PathVariable("id") String id, Model model) {
        // получаем данные по коду пользователя
        idChessman = Long.valueOf(id);
        Optional<ChessMan> chessManServiceById = chessManService.getBy_Id(idChessman);
        if (chessManServiceById.isPresent()) {
            chessMan = chessManServiceById.get();
            Game_Application gameApplication = gameApplicationService.getByChessMan(chessMan);
            if (gameApplication == null) {
                gameApplication = Game_Application.builder()
                        .id(0L)
                        .chessMan(chessMan)
                        .color(Chess_Color.WHITE.name())
                        .gameTime(Time.valueOf("00:30:00"))
                        .busy(false)
                        .build();

            }
            logger.info("change: " + gameApplication.toString());
            model.addAttribute("gameApp", gameApplication);
        }

        return "gameApp_page";
    }

    @RequestMapping(value = "/gameApp_page/saveApp", method = RequestMethod.POST)
    public String saveApp(@ModelAttribute("gameApp") Game_Application game_application) {
        logger.info("saveApp: время=" + game_application.getGameTime());
        game_application.setChessMan(chessMan);
        logger.info("saveApp: " + game_application.toString());
//        this.gameApplicationService.save(game_application);
        return "redirect:/home_page/" + String.valueOf(idChessman);
    }

}
