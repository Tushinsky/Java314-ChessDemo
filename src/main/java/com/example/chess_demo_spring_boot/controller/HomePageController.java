package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import com.example.chess_demo_spring_boot.dto.ChallengeDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
    @GetMapping(value = "/home/{id}")
    public String getHomePage(@PathVariable("id") Long id, Model model) {

        // получаем текущего пользователя
        Optional<ChessMan> chessManServiceById = chessManService.getBy_Id(id);
        if(chessManServiceById.isPresent()) {
            chessMan = chessManServiceById.get();
            // список сыгранных партий
            setHistory(model);

            /*
             Заявка текущего пользователя для участия в играх. Если пользователь не подавал заявку,
             то список остальных для него недоступен
             */
            GameApplication game_application = gameApplicationService.getByChessMan(chessMan);
            if(game_application != null) {
                logger.info(game_application.toString());
                model.addAttribute("gameApp", game_application);
                // список пользователей, которые отправили приглашения для участия в игре
                List<ChallengeDto> challengesWho = challengeService.getAllByOpponent(chessMan);
                model.addAttribute("whoChallenge", challengesWho);
//                setOpponentsWhoChallenge(model);
                // список пользователей, которым отправлены приглашения для участия в игре
                List<ChallengeDto> challengesWhom = challengeService.getAllByChessMan(chessMan);
                model.addAttribute("whomChallenge", challengesWhom);
//                setOpponentsWhomChallenge(model);
                // список пользователей, зарегистрированных для участия в играх
                setChessManApplications(model, challengesWho, challengesWhom);


            }


            return "home";
        }
        return "redirect:/start";
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

    private void setChessManApplications(Model model, List<ChallengeDto> challengesWho,
                                         List<ChallengeDto> challengesWhom) {
        /*
         список пользователей, подавших заявки для участия в играх (исключая текущего), за исключением
         тех, кого пользователь пригласил на игру, и тек, кто пригласил его
         */
        List<GameApplication> appList = gameApplicationService.getAllByChessmanIsNot(chessMan);
        if (appList != null) {
            List<GameApplicationDto> applicationDtos = new ArrayList<>();
            if(!appList.isEmpty()) {
                appList.forEach(item -> {
                    ChallengeDto dtoWho = challengesWho.stream().filter(who ->
                                    who.getChessManName().equals(item.getChessMan().getNic())).findFirst().orElse(null);
                    ChallengeDto dtoWhom = challengesWhom.stream().filter(whom ->
                            whom.getChessManName().equals(item.getChessMan().getNic())).findFirst().orElse(null);
                    if (dtoWho != null || dtoWhom != null) {
                        GameApplicationDto dto = GameApplicationDto.builder().id(item.getId())
                                .nic(item.getChessMan().getNic())
                                .color(item.getColor())
                                .gameTime(item.getGameTime())
                                .busy(item.isBusy())
                                .build();
                        applicationDtos.add(dto);
                    }

                });

                model.addAttribute("appList", applicationDtos);
            }
        }
    }

    /**
     * Получает список оппонентов, которых пригласил сыграть текущий пользователь, и выводит его в модели страницы
     * @param model модель страницы
     */
    private void setOpponentsWhomChallenge(Model model) {
        List<ChallengeDto> challengesWhom = challengeService.getAllByChessMan(chessMan);
        model.addAttribute("whomChallenge", challengesWhom);
    }

    /**
     * Получает список оппонентов, которые пригласили сыграть текущего пользователя, и выводит его в модели страницы
     * @param model модель страницы
     */
    private void setOpponentsWhoChallenge(Model model) {
        List<ChallengeDto> challengesWho = challengeService.getAllByOpponent(chessMan);
        model.addAttribute("whoChallenge", challengesWho);
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


}
