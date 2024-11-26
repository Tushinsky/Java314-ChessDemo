package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.dto.ChessManDto;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.CityService;
import com.example.chess_demo_spring_boot.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChessManController {
    private final ChessManService chessManService;
    private final CityService cityService;
    private final CountryService countryService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "chessman_page", method = RequestMethod.GET)
    public String getUserList(Model model) {
        List<ChessMan> chessManList = chessManService.getAll();
        if(chessManList != null) {
            model.addAttribute("chessman", new ChessMan());
            model.addAttribute("chessmanList", chessManService.getAll());
        }
        return "chessman_page";
    }
}
