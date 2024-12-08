package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Role;
import com.example.chess_demo_spring_boot.domain.State;
import com.example.chess_demo_spring_boot.dto.ChessManDto;
import com.example.chess_demo_spring_boot.service.ChessManService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final ChessManService chessManService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new ChessManDto());
        return "login";
    }

    /**
     * Аутентификация входящего пользователя. Если пользователь с данным именем есть, проверяем пароль, его права
     * доступа, состояние его аккаунта. В зависимости от результата направляем его либо на страницу администратора,
     * либо на домашнюю страницу
     * @param chessManDto шаблон входящего пользователя
     * @return страница с данными
     */
    @PostMapping(value = "/logon")
    public String authenticateUser(@Validated ChessManDto chessManDto, Model model) {
        // получаем пользователя по нику
        logger.info("ChessManDto: " + chessManDto.toString());
        ChessMan authChessMan = chessManService.getByName(chessManDto.getName());

        if(authChessMan != null) {
            logger.info("authChessMan: " + authChessMan);
            // проверяем пароль
            if(Objects.equals(authChessMan.getPassword(), chessManDto.getPassword())) {
                if(authChessMan.getRole().equals(String.valueOf(Role.USER))) {
                    if(authChessMan.getState().equals(String.valueOf(State.ACTIVE)) ||
                            authChessMan.getState().equals(String.valueOf(State.INACTIVE))) {
                        return "redirect:/home_page/" + authChessMan.getId();
                    }
                } else {
                    //перенаправляем на домашнюю страницу администратора
                    return "redirect:/admin_page";
                }
            } else {
                model.addAttribute("errorMessage", "Неверный пароль!");
            }

        } else {
            model.addAttribute("errorMessage", "Пользователь с таким именем не найден! " +
                    "Попробуйте ещё раз или зарегистрируйтесь!");
        }

        // если такого пользователя нет, перенаправляем на стартовую страницу
        return "login";
    }
}
