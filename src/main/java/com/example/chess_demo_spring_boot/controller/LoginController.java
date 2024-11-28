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

    @PostMapping(value = "/logon")
    public String authenticateUser(@Validated ChessManDto chessManDto) {
        // получаем пользователя по нику
        logger.info("ChessManDto: " + chessManDto.toString());
        ChessMan authChessMan = chessManService.getByName(chessManDto.getName());
        logger.info("authChessMan: " + authChessMan.toString());
        if(authChessMan != null) {
            // проверяем пароль
            if(Objects.equals(authChessMan.getPassword(), chessManDto.getPassword())) {
                // проверяем права пользователя - USER or ADMIN
                if(authChessMan.getRole().equals(String.valueOf(Role.USER))) {
                    // если это обычный пользователь и его аккаунт не был удалён или заблокирован (DELETE or BANNED)
                    if(authChessMan.getState().equals(String.valueOf(State.ACTIVE)) ||
                            authChessMan.getState().equals(String.valueOf(State.INACTIVE))) {
                        //перенаправляем на домашнюю страницу пользователя
                        return "redirect:/home_page/" + authChessMan.getId();
                    }
                } else {
                    //перенаправляем на домашнюю страницу администратора
                    return "redirect:/admin_page";
                }
            }

        }


        return "redirect:/start_page";
    }
}
