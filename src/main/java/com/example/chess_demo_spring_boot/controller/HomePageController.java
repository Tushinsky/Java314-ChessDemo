package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import com.example.chess_demo_spring_boot.dto.ChessManDto;
import com.example.chess_demo_spring_boot.dto.GameApplicationDto;
import com.example.chess_demo_spring_boot.dto.HistoryDto;
import com.example.chess_demo_spring_boot.service.ChallengeService;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.GameApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final ChallengeService challengeService;
    private ChessMan chessMan;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/home/{id}")
    public String getHomePage(@PathVariable("id") Long id, Model model) {

        // получаем текущего пользователя
        Optional<ChessMan> chessManServiceById = chessManService.getBy_Id(id);
        if(chessManServiceById.isPresent()) {
            chessMan = chessManServiceById.get();
            // список сыгранных партий
            setHistory(model);

            // заявка текущего пользователя для участия в играх
            GameApplication game_application = gameApplicationService.getByChessMan(chessMan);
            if(game_application != null) {
                logger.info(game_application.toString());
                model.addAttribute("gameApp", game_application);
            }

            // список пользователей, зарегистрированных для участия в играх
            setApplications(model);

            // список пользователей, которым отправлены приглашения для участия в игре
            setOpponentsWhoChallenge(model);
            // список пользователей, которые отправили приглашения для участия в игре
            setOpponentsWhomChallenge(model);
            return "home";
        }
        return "redirect:/start";
    }

    /**
     * Получает список оппонентов, которых пригласил сыграть текущий пользователь, и выводит его в модели страницы
     * @param model модель страницы
     */
    private void setOpponentsWhomChallenge(Model model) {
        List<Challenge> challenges = challengeService.getAllByChessMan(chessMan);
        model.addAttribute("whomChallenge", challenges);
    }

    /**
     * Получает список оппонентов, которые пригласили сыграть текущего пользователя, и выводит его в модели страницы
     * @param model модель страницы
     */
    private void setOpponentsWhoChallenge(Model model) {
        List<Challenge> challenges = challengeService.findAllByOpponent(chessMan);
        model.addAttribute("whoChallenge", challenges);
    }

    /**
     * Добавляет запись в таблицу приглашений к игре других оппонентов
     * @param id код записи из таблицы заявок на игру
     * @return возвращает на домашнюю страницу
     */
    @RequestMapping(value = "/challenge/{id}")
    public String addChallenge(@PathVariable("id") Long id) {
        GameApplication game_application = gameApplicationService.getById(id);
        ChessMan opponent = game_application.getChessMan();
        Challenge challenge = Challenge.builder()
                .chessMan(chessMan)
                .opponent(opponent)
                .takeIt(false)
                .build();
        logger.info(challenge.toString());
        challengeService.saveChallenge(challenge);
        return "redirect:/home/" + chessMan.getId();
    }

    private void setHistory(Model model) {
        // список сыгранных партий текущего пользователя
        List<HistoryDto> historyList = chessManService.getAllHistoryByChessMan(chessMan);
        logger.info("historyList: size=" + historyList.size());

        model.addAttribute("chessman", chessMan);
        if (historyList.size() > 0) {
            model.addAttribute("historyList", historyList);

        }
    }

    private void setApplications(Model model) {
        // список пользователей, подавших заявки для участия в играх (исключая текущего)
        List<GameApplicationDto> appList = gameApplicationService.getAllByChessmanIsNot(chessMan);
        if (appList != null) {
            model.addAttribute("appList", appList);
        }
    }
}
