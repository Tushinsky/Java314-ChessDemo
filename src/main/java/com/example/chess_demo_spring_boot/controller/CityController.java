package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.City;
import com.example.chess_demo_spring_boot.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @RequestMapping(value = "cities", method = RequestMethod.GET)
    public String getCityList(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("cityList", this.cityService.getAll());

        return "cities";
    }

    @RequestMapping(value = "/cities/add", method = RequestMethod.POST)
    public String addCity(@ModelAttribute("city") City city) {
        if(city.getId() == 0) {
            this.cityService.save(city);
        } else {
            this.cityService.save(city);
        }

        return "redirect:/cities";
    }

    @RequestMapping("/removeCity/{id}")
    public String removeCity(@PathVariable("id") Long id) {
        this.cityService.removeCity(id);
        return "redirect:/cities";
    }

    @RequestMapping("/updateCity/{id}")
    public String updateCity(@PathVariable("id") Long id, Model model) {
        model.addAttribute("city", this.cityService.getBy_Id(id));
        model.addAttribute("cityList", this.cityService.getAll());

        return "cities";
    }
}
