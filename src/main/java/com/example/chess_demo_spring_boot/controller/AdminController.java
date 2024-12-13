package com.example.chess_demo_spring_boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {


    @GetMapping(value = "/admin")
    public String getAdminPage() {
        return "admin";
    }


    @RequestMapping(value = "/cities")
    public String getItemPage() {
        return "redirect:/cities";
    }

    @RequestMapping(value = "/countries")
    public String getCountryPage() {
        return "redirect:/countries";
    }

    @RequestMapping(value = "/chessman")
    public String getChessmanPage() {
        return "redirect:/chessman";
    }

}
