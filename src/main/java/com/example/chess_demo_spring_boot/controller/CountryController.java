package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.domain.City;
import com.example.chess_demo_spring_boot.domain.Country;
import com.example.chess_demo_spring_boot.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    @RequestMapping(value = "countries", method = RequestMethod.GET)
    public String getCountryList(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute("countryList", this.countryService.getAll());

        return "countries";
    }

    @RequestMapping(value = "/countries/add", method = RequestMethod.POST)
    public String addCountry(@ModelAttribute("country") Country country) {
        if(country.getId() == 0) {
            this.countryService.save(country);
        } else {
            this.countryService.save(country);
        }

        return "redirect:/countries";
    }

    @RequestMapping("/removeCountry/{id}")
    public String removeCountry(@PathVariable("id") Long id) {
        this.countryService.removeCountry(id);
        return "redirect:/countries";
    }

    @RequestMapping("/updateCountry/{id}")
    public String updateCountry(@PathVariable("id") Long id, Model model) {
        model.addAttribute("country", this.countryService.getBy_Id(id));
        model.addAttribute("countryList", this.countryService.getAll());

        return "countries";
    }
}
