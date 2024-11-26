package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    Optional<City> getBy_Id(Long id);
    City getByName(String name);
    List<City> getAll();
    void save(City city);
    void updateCity(City city);

    void removeCity(Long id);
}
