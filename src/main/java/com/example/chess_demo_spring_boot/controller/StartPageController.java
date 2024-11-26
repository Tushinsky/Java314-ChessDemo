package com.example.chess_demo_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartPageController {

    @RequestMapping("/start_page")
    public String getStartPage() {
        return "start_page";
    }
}
