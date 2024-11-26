package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Country;

import java.util.List;

public interface CountryService {
    Country getBy_Id(Long id);
    Country getByName(String name);
    List<Country> getAll();
    void save(Country country);
    void updateCountry(Country country);
    void removeCountry(Long id);
}
