package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.service.ChessManService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final ChessManService chessManService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home_page/{id}")
    public String getHomePage(@PathVariable("id") String id, Model model) {
        Optional<ChessMan> chessMan = chessManService.getBy_Id(Long.valueOf(id));
        model.addAttribute("name", chessMan.get().getName());
        return "home_page";
    }
}
