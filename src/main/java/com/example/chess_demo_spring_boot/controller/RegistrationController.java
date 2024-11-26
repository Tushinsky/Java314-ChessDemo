package com.example.chess_demo_spring_boot.controller;

import com.example.chess_demo_spring_boot.service.CityService;
import com.example.chess_demo_spring_boot.service.CountryService;
import com.example.chess_demo_spring_boot.service.RegistrationService;
import com.example.chess_demo_spring_boot.dto.RegistrationDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final CityService cityService;
    private final CountryService countryService;
    // 1. render html page 2. submit registration -> nik-password
    @GetMapping("/registration")
    public String registerPage(Model model){
        model.addAttribute("registration", new RegistrationDto());
        model.addAttribute("cityList", cityService.getAll());
        model.addAttribute("countryList", countryService.getAll());
        return "registration";
    }

    @PostMapping(value="/registration")
    public String registerUser(@Validated RegistrationDto registrationData){
        if(registrationService.RegisterUser(registrationData)) {
            return "redirect:/login"; // redirect to login page
        } else {
            return "redirect:/start_page"; // redirect to start page
        }
    }
}
