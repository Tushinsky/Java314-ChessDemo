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
import org.springframework.web.bind.annotation.RequestMethod;

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
    private GameApplication game_application;
    @GetMapping(value = "/home/{id}")
    public String getHomePage(@PathVariable("id") Long id, Model model) {

        // получаем текущего пользователя
        Optional<ChessMan> chessManServiceById = chessManService.getBy_Id(id);
        if(chessManServiceById.isPresent()) {
            chessMan = chessManServiceById.get();
            model.addAttribute("chessman", chessMan);
//            model.addAttribute("historyMessage", "");
            // список сыгранных партий
//            setHistory(model);
//
            /*
             Заявка текущего пользователя для участия в играх. Если пользователь не подавал заявку,
             то список остальных для него недоступен
             */
            game_application = gameApplicationService.getByChessMan(chessMan);
            if(game_application != null) {
//                logger.info(game_application.toString());
                model.addAttribute("gameApp", game_application);
//                // список пользователей, которые отправили приглашения для участия в игре
//                List<ChallengeDto> challengesWho = challengeService.getAllByOpponent(chessMan);
//                model.addAttribute("whoChallenge", challengesWho);
////                setOpponentsWhoChallenge(model);
//                // список пользователей, которым отправлены приглашения для участия в игре
//                List<ChallengeDto> challengesWhom = challengeService.getAllByChessMan(chessMan);
//                model.addAttribute("whomChallenge", challengesWhom);
////                setOpponentsWhomChallenge(model);
//                // список пользователей, зарегистрированных для участия в играх
//                setChessManApplications(model, challengesWho, challengesWhom);


            }


            return "home";
        }
        return "redirect:/start";
    }

    @GetMapping(value = "/history")
    public String getHistory(Model model) {
        // список сыгранных партий текущего пользователя
        List<HistoryDto> historyList = chessManService.getAllHistoryByChessMan(chessMan);
        logger.info("historyList: size=" + historyList.size());
        String message;
        StringBuilder tableData = new StringBuilder();
        String headers = "";
        if (historyList.size() == 0) {

            message = "Ещё нет ни одной проведённой партии.";

        } else {
            // формируем данные для вставки в таблицу
            message = "Проведённые партии";
            headers = "<tr><th width='150'>Оппонент</th><th width='250'>Дата проведения партии</th>" +
                    "<th width='100'>Результат</th><th width='100'>Ходы</th></tr>";
            // данные
            for (HistoryDto item : historyList) {
                tableData.append("<tr>").append("<td>")
                        .append(item.getOpponent()).append("</td>")
                        .append("<td>").append(item.getPartyDate()).append("</td>")
                        .append("<td>").append(item.getResult()).append("</td>")
                        .append("<td><a href=\"/progress/").append(item.getId()).append("\"")
                        .append("    target=\"_blank\">Посмотреть</a></td>")
                        .append("</tr>");
            }
        }
        model.addAttribute("chessman", chessMan);
        model.addAttribute("gameApp", game_application);
        model.addAttribute("headerMessage", message);
        model.addAttribute("tableData", (headers + tableData));
        return "home";
    }

    @GetMapping(value = "/whomChallenge")
    public String getWhomChallenge(Model model) {
        List<ChallengeDto> challengesWhom = challengeService.getAllByChessMan(chessMan);
        String message;
        StringBuilder tableData = new StringBuilder();
        String headers = "";
//        model.addAttribute("gameApp", game_application);
        if (challengesWhom.size() == 0) {
            message = "Вы ещё никого не пригласили сыграть!";
        } else {
            message = "Приглашаете сыграть";
            headers = "<tr>" +
                    "<th>Ник</th><th>Вызов принят</th><th>Отменить</th>" +
                    "</tr>";
            for(ChallengeDto item : challengesWhom) {
                tableData.append("<tr><td>").append(item.getOpponentName()).append("</td>")
                        .append("<td>").append(item.isTakeIt()).append("</td>")
                        .append("<td>").append("<a href=\"/cancel/").append(item.getId()).append(
                                "\" ").append(">Отменить</a>").append("</td>")
                        .append("</tr>");
            }
        }
        model.addAttribute("chessman", chessMan);
        model.addAttribute("headerMessage", message);
        model.addAttribute("tableData", (headers + tableData));
        return "home";
    }

    @GetMapping(value = "/whoChallenge")
    public String getWhoChallenge(Model model) {
        List<ChallengeDto> challengesWho = challengeService.getAllByOpponent(chessMan);
        String message;
        StringBuilder tableData = new StringBuilder();
        String headers = "";
        if(challengesWho.size() == 0) {
            message = "Вас ещё никто не пригласил сыграть.";
        } else {
            message = "Приглашают с ними сыграть";
            headers = "<tr>" +
                    "<th>Ник</th>" + "<th>Вызов принят</th>" + "<th>Действие</th>" +
                    "</tr>";
            for(ChallengeDto item : challengesWho) {
                tableData.append("<tr>\n").append("<td>").append(item.getChessManName()).append("</td>\n")
                        .append("<td>").append(item.isTakeIt() ? "yes" : "no").append("</td>\n")
                        .append((item.isTakeIt() ? "<td><a href=\"/take/" + item.getId() +
                                "&false\">Отказаться</a></td>\n" : "<td><a href=\"/take/" + item.getId() +
                                "&true\">Принять</a></td>\n"))
                        .append("</tr>");
            }
        }
        model.addAttribute("chessman", chessMan);
        model.addAttribute("headerMessage", message);
        model.addAttribute("tableData", (headers + tableData));
        return "home";
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
//                    logger.info(item.getChessMan().getNic());
                    ChallengeDto dtoWho = challengesWho.stream().filter(who ->
                                    who.getChessManName().equals(item.getChessMan().getNic())).findFirst().orElse(null);
                    ChallengeDto dtoWhom = challengesWhom.stream().filter(whom ->
                            whom.getOpponentName().equals(item.getChessMan().getNic())).findFirst().orElse(null);
                    if ((dtoWhom == null) && (dtoWho == null)) {
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
        model.addAttribute("chessman", chessMan);
        model.addAttribute("whomChallenge", challengesWhom);

    }

    /**
     * Получает список оппонентов, которые пригласили сыграть текущего пользователя, и выводит его в модели страницы
     * @param model модель страницы
     */
    private void setOpponentsWhoChallenge(Model model) {
        List<ChallengeDto> challengesWho = challengeService.getAllByOpponent(chessMan);
        model.addAttribute("chessman", chessMan);
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

    /**
     * Удаляет вызов, сделанный пользователем оппоненту
     * @param id код записи из базы данных
     * @return домашнюю страницу с изменениями
     */
    @RequestMapping(value = "/cancel/{id}")
    public String cancelChallenge(@PathVariable("id") Long id) {
        challengeService.removeChallenge(id);
        return "redirect/whomChallenge";
    }

    @RequestMapping(value = "/take/{param}", method = RequestMethod.GET)
    public String takeChallenge(@PathVariable("param") String param) {
        String[] parameters = param.split("&");// получаем массив параметров
        Long id = Long.valueOf(parameters[0]);
        boolean takeIt = parameters[1].equals("true");
        Optional<Challenge> serviceBy_id = challengeService.getBy_Id(id);
        if (serviceBy_id.isPresent()) {
            Challenge challenge = serviceBy_id.get();
            challenge.setTakeIt(takeIt);
            challengeService.saveChallenge(challenge);
        }
        return "redirect:/whoChallenge";
    }

    @GetMapping(value = "/list")
    public String getOpponentList(Model model) {
        return "home";
    }

}
