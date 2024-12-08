package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Game_Application;
import com.example.chess_demo_spring_boot.dto.HistoryDto;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.Game_ApplicationService;
import com.example.chess_demo_spring_boot.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final ChessManService chessManService;
    private final Game_ApplicationService gameApplicationService;
    private ChessMan chessMan;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home_page/{id}")
    public String getHomePage(@PathVariable("id") String id, Model model) {

        // получаем текущего пользователя
        Long idChessman = Long.valueOf(id);
        Optional<ChessMan> chessManServiceById = chessManService.getBy_Id(idChessman);
        if(chessManServiceById.isPresent()) {
            chessMan = chessManServiceById.get();
            // передаём полученные данные в модель страницы для вывода
            List<HistoryDto> historyList = chessManService.getAllHistoryByChessMan(chessMan);
            logger.info("historyList: size=" + historyList.size());

            model.addAttribute("chessman", chessMan.getName());
            if (historyList.size() > 0) {
                model.addAttribute("historyList", historyList);

            }

            Game_Application game_application = gameApplicationService.getByChessMan(chessMan);
            model.addAttribute("gameApp", game_application);

            List<Game_Application> appList = gameApplicationService.getAllByChessmanIsNot(chessMan);
            if (appList != null) {
//                logger.info("appList: size=" + appList.size());
//                appList.forEach(item -> logger.info(item.getChessMan().toString()));
                model.addAttribute("appList", appList);
            }

            return "home_page";
        }
        return "redirect:/start_page";
    }

    @RequestMapping(value = "/home_page/addApp", method = RequestMethod.POST)
    public String addApp(@ModelAttribute("gameApp") Game_Application game_application) {
        game_application.setChessMan(chessMan);
        logger.info(game_application.toString());
//        this.gameApplicationService.save(game_application);
        return "redirect:/home_page/" + chessMan.getId().toString();
    }
}
