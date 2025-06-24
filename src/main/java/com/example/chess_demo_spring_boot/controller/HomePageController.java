package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.Challenge;
import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.GameApplication;
import com.example.chess_demo_spring_boot.dto.ChallengeDto;
import com.example.chess_demo_spring_boot.dto.GameApplicationDto;
import com.example.chess_demo_spring_boot.dto.HistoryDto;
import com.example.chess_demo_spring_boot.service.ChallengeService;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.ChessPartyService;
import com.example.chess_demo_spring_boot.service.GameApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final ChessPartyService chessPartyService;
    /**
     * Выводит домашнюю страницу пользователя
     * @param id идентификатор пользователя из базы данных
     * @param model модель страницы
     * @return домашнюю страницу пользователя с меню
     */
    @GetMapping(value = "/home/{id:\\d+}")
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
                model.addAttribute("gameApp", game_application);
            }


            return "home";
        }
        return "redirect:/start";
    }

    @GetMapping(value = "/history")
    public String getHistory(Model model) {
        // список сыгранных партий текущего пользователя
        List<HistoryDto> historyList = chessManService.getAllHistoryByChessMan(chessMan, true);
        logger.info("historyList: size=" + historyList.size());
        String message;
        StringBuilder tableData = new StringBuilder();
//        String headers = null;
        StringBuilder listUnit = new StringBuilder();
        if (historyList.size() == 0) {

            message = "Ещё нет ни одной проведённой партии.";

        } else {
            // формируем данные для вставки в таблицу
            message = "Проведённые партии";
            tableData.append("<tr><th width='150'>Оппонент</th><th width='250'>Дата проведения партии</th>" +
                    "<th width='100'>Результат</th><th width='100'>Ходы</th></tr>");
            // данные
            for (HistoryDto item : historyList) {
                tableData.append("<tr>").append("<td>")
                        .append("<td>").append(item.getPartyDate()).append("</td>")
                        .append("<td>").append(item.getResult()).append("</td>")
                        .append("<td><a href=\"/progress/").append(item.getId()).append("\"")
                        .append("    target=\"_blank\">Посмотреть</a></td>")
                        .append("</tr>");
                listUnit.append("<div class=\"list_unit\" data-tooltip=\"").append(item.getResult())
                        .append("\">").append("<span>").append(item.getPartyDate()).append(":\n")
                        .append(item.getOpponent()).append("</span>")
                        .append("</div>");
            }
        }
        setModelAttribute(model, message,tableData.toString());
        model.addAttribute("listUnit", listUnit);
        return "home";
    }

    @GetMapping(value = "/whomChallenge")
    public String getWhomChallenge(Model model) {
        List<ChallengeDto> challengesWhom = challengeService.getAllByChessMan(chessMan);
        String message;
        StringBuilder tableData = new StringBuilder();
        StringBuilder list_unitData = new StringBuilder();
//        String headers = null;
//        model.addAttribute("gameApp", game_application);
        if (challengesWhom.size() == 0) {
            message = "Вы ещё никого не пригласили сыграть!";
        } else {
            message = "Приглашаете сыграть";
            tableData.append("<tr>" +
                    "<th>Ник</th><th>Вызов принят</th><th>Действие</th>" +
                    "</tr>");
            for(ChallengeDto item : challengesWhom) {
                boolean isTakeIt = item.isTakeIt();
                String takeIt;
                String linkA;
                if(isTakeIt) {
                    takeIt = "да";
                    linkA = "<a href=\"/cancel/" + item.getId() + "\">Отменить</a>" + "/" +
                            "<a href=\"/game/" + item.getId() + "\">Играть</a>";
                } else {
                    takeIt = "нет";
                    linkA = "<a href=\"/cancel/" + item.getId() + "\">Отменить</a>";
                }
                tableData.append("<tr><td>").append(item.getOpponentName()).append("</td>")
                        .append("<td>").append(takeIt).append("</td>")
                        .append("<td>").append(linkA).append("</td>")
                        .append("</tr>");
                list_unitData.append("<div class=\"list_unit\" data-tooltip=\"it's a ")
                        .append(item.getOpponentName()).append("\">")
                        .append("<span>").append(item.getOpponentName()).append("</span>")
                        .append("</div>");
            }
        }
        setModelAttribute(model, message,tableData.toString());
        model.addAttribute("list_unitData", list_unitData);
        return "home";
    }

    @GetMapping(value = "/whoChallenge")
    public String getWhoChallenge(Model model) {
        List<ChallengeDto> challengesWho = challengeService.getAllByOpponent(chessMan);
        String message;
        StringBuilder tableData = new StringBuilder();
        StringBuilder list_unitData = new StringBuilder();
//        String headers = "";
        if(challengesWho.size() == 0) {
            message = "Вас ещё никто не пригласил сыграть.";
        } else {
            message = "Приглашают с ними сыграть";
            tableData.append("<tr>" +
                    "<th>Ник</th>" + "<th>Вызов принят</th>" + "<th>Действие</th>" +
                    "</tr>");
            for(ChallengeDto item : challengesWho) {
                tableData.append("<tr>\n").append("<td>").append(item.getChessManName()).append("</td>\n")
                        .append("<td>").append(item.isTakeIt() ? "yes" : "no").append("</td>\n")
                        .append((item.isTakeIt() ? "<td><a href=\"/take/" + item.getId() +
                                "/false\">Отказаться</a></td>\n" : "<td><a href=\"/take/" + item.getId() +
                                "/true\">Принять</a></td>\n"))
                        .append("</tr>");
                list_unitData.append("<div class=\"list_unit\" data-tooltip=\"it's a ")
                        .append(item.getChessManName()).append("\">")
                        .append("<span>").append(item.getChessManName()).append("</span>")
                        .append("</div>");
            }
        }
        setModelAttribute(model, message, tableData.toString());
        model.addAttribute("list_unitData", list_unitData);
        return "home";
    }

    /**
     * Список пользователей, подавших заявки для участия в играх (исключая текущего), за исключением
     * тех, кого пользователь пригласил на игру, и тек, кто пригласил его
     * @param model модель страницы
     * @param challengesWho список пользователей, которые пригласили текущего пользователя
     * @param challengesWhom список пользователей, которых пригласил текущий пользователь
     */
    private void setChessManApplications(Model model, List<ChallengeDto> challengesWho,
                                         List<ChallengeDto> challengesWhom) {
        String message;
        StringBuilder list_unitData = new StringBuilder();
        List<GameApplication> appList = gameApplicationService.getAllByChessmanIsNot(chessMan);
        if (appList != null) {
            List<GameApplicationDto> applicationDtos = new ArrayList<>();
            appList.forEach(item -> {
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
            message = "Ваши оппоненты";
            // данные
            for (GameApplicationDto dto : applicationDtos) {
                String busy = dto.isBusy() ? "сейчас занят" : "сейчас свободен";
                list_unitData.append("<div class=\"list_unit\" data-tooltip=\"")
                        .append(busy).append("\">")
                        .append("<span>")
                        .append(dto.getNic()).append("\t")
                        .append(dto.getGameTime()).append("\t").append(dto.getColor()).append("\t")
                        .append("<a href=\"/challenge/").append(dto.getId())
                        .append("\">Вызвать</a></span>")
                        .append("</div>");
            }
        } else {
            message = "Нет зарегистрированных пользователей!";
        }
        model.addAttribute("chessman", chessMan);
        model.addAttribute("gameApp", game_application);
        model.addAttribute("list_unitData", list_unitData);
        model.addAttribute("headerMessage", message);
//        setModelAttribute(model, message,(headers + tableData));
    }

    /**
     * Получает список оппонентов, которых пригласил сыграть текущий пользователь, и выводит его в модели страницы
     * @param model модель страницы
     */
    private void setOpponentsWhomChallenge(Model model) {
        List<ChallengeDto> challengesWhom = challengeService.getAllByChessMan(chessMan);
        model.addAttribute("chessman", chessMan);
        model.addAttribute("gameApp", game_application);
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
        model.addAttribute("gameApp", game_application);
    }

    /**
     * Добавляет запись в таблицу приглашений к игре других оппонентов
     * @param id код записи из таблицы заявок на игру
     * @return возвращает на домашнюю страницу
     */
    @GetMapping(value = "/challenge/{id}")
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
        return "redirect:/list";
    }

    /**
     * Удаляет вызов, сделанный пользователем оппоненту
     * @param id код записи из базы данных
     * @return домашнюю страницу с изменениями
     */
    @DeleteMapping(value = "/cancel/{id:\\d+}")
    public String cancelChallenge(@PathVariable("id") Long id) {
        challengeService.removeChallenge(id);
        return "redirect:/whomChallenge";
    }

    /**
     * Принимает или отменяет приглашение к игре, полученное от оппонента
     * @param id идентификатор записи в таблице
     * @param takeIt флаг принятия/отказа вызова
     * @return домашнюю страницу с данными о принятых или отменённых приглашениях
     */
    @GetMapping(value = "/take/{id:\\d+}/{takeIt}")
    public String takeChallenge(@PathVariable("id") Long id, @PathVariable("takeIt") boolean takeIt) {
        Optional<Challenge> serviceBy_id = challengeService.getBy_Id(id);
        if (serviceBy_id.isPresent()) {
            Challenge challenge = serviceBy_id.get();
            challenge.setTakeIt(takeIt);
            challengeService.saveChallenge(challenge);
        }
        return "redirect:/whoChallenge";
    }

    /**
     * Получает список партнёров по игре и выводит на страницу
     * @param model модель данных страницы
     * @return домашнюю страницу с данными
     */
    @GetMapping(value = "/list")
    public String getOpponentList(Model model) {
        List<ChallengeDto> challengesWhom = challengeService.getAllByChessMan(chessMan);
        List<ChallengeDto> challengesWho = challengeService.getAllByOpponent(chessMan);
        setChessManApplications(model, challengesWho, challengesWhom);
        return "home";
    }

    @GetMapping(value = "/exit")
    public String exit() {
        return "redirect:/start";
    }
    /**
     * Создаёт игровую партию и пересылает на страницу игры
     * @param id идентификатор оппонента, с которым создаётся игровая партия
     * @return страницу с игрой
     */
    @GetMapping(value = "/game/{id:\\d+}")
    public String goToGame(@PathVariable("id") Long id) {
        ChessMan opponent = chessManService.getBy_Id(id).orElseThrow();
        chessPartyService.addParty(chessMan, opponent);
        return "home";
    }

    /**
     * Отображает на странице информацию по отложенным партиям
     * @param model модель страницы
     * @return таблицу с информацией по отложенным партиям
     */
    @GetMapping(value = "/put_aside")
    public String putAsideParty(Model model) {
        // список сыгранных партий текущего пользователя
        List<HistoryDto> historyList = chessManService.getAllHistoryByChessMan(chessMan, false);
        logger.info("historyList: size=" + historyList.size());
        String message;
        StringBuilder tableData = new StringBuilder();
//        String headers = null;
        if (historyList.isEmpty()) {

            message = "Ещё нет ни одной начатой партии.";

        } else {
            // формируем данные для вставки в таблицу
            message = "Отложенные партии";
            tableData.append("<tr><th width='150'>Оппонент</th><th width='250'>Дата начала партии</th>" +
                    "<th width='100'>Результат</th><th width='100'>Действие</th></tr>");
            // данные
            for (HistoryDto item : historyList) {
                tableData.append("<tr>").append("<td>")
                        .append(item.getOpponent()).append("</td>")
                        .append("<td>").append(item.getPartyDate()).append("</td>")
                        .append("<td>").append(item.getResult()).append("</td>")
                        .append("<td><a href=\"/game-progress/").append(item.getId()).append("/")
                        .append(chessMan.getId()).append("\"")
                        .append("    target=\"_blank\">Продолжить</a></td>")
                        .append("</tr>");
            }
        }
        setModelAttribute(model, message, tableData.toString());
        return "home";
    }

    private void setModelAttribute(Model model, String message, String tableData) {
        model.addAttribute("chessman", chessMan);
        model.addAttribute("gameApp", game_application);
        model.addAttribute("headerMessage", message);
        model.addAttribute("tableData", tableData);
    }
}
