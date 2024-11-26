package com.example.chess_demo_spring_boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {


    @GetMapping(value = "/admin_page")
    public String getAdminPage() {
        return "admin_page";
    }


    @RequestMapping(value = "/cities")
    public String getCityPage() {
        return "redirect:/cities";
    }

    @RequestMapping(value = "/countries")
    public String getCountryPage() {
        return "redirect:/countries";
    }

    @RequestMapping(value = "/chessman_page")
    public String getChessmanPage() {
        return "redirect:/chessman_page";
    }

}
