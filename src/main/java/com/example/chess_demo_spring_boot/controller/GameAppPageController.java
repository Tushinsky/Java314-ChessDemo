package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.ChessColor;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.GameApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Optional;

/**
 * Контроллер для отображения страницы регистрации/изменения заявки на участие в игре
 */
@Controller
@RequiredArgsConstructor
public class GameAppPageController {
    private final GameApplicationService gameApplicationService;
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
    @RequestMapping("/gameApp/{id}")
    public String changeApplication(@PathVariable("id") String id, Model model) {
        // получаем данные по коду пользователя
        idChessman = Long.valueOf(id);
        Optional<ChessMan> chessManServiceById = chessManService.getBy_Id(idChessman);
        if (chessManServiceById.isPresent()) {
            chessMan = chessManServiceById.get();
            GameApplication gameApplication = gameApplicationService.getByChessMan(chessMan);
            if (gameApplication == null) {
                gameApplication = GameApplication.builder()
                        .id(0L)
                        .chessMan(chessMan)
                        .color(ChessColor.WHITE.name())
                        .gameTime(Time.valueOf("00:30:00"))
                        .busy(false)
                        .build();

            }
            logger.info("change: " + gameApplication.toString());
            model.addAttribute("gameApp", gameApplication);
        }

        return "gameApp";
    }
//@ModelAttribute("gameApp")
    @RequestMapping(value = "/gameApp/saveApp", method = RequestMethod.POST)
    public String saveApp(@RequestBody @ModelAttribute("gameApp") GameApplication gameApplication) {
        logger.info("saveApp: время=" + gameApplication.getGameTime());
        gameApplication.setChessMan(chessMan);
        logger.info("saveApp: " + gameApplication);
        this.gameApplicationService.save(gameApplication);
        return "redirect:/home/" + idChessman;
    }

}
