package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.City;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class CityDto {
    Long id;
    String name;

    public static CityDto from(City city) {
        return new CityDto(city.getId(), city.getName());
    }

    public static List<CityDto> from(List<City> cityList) {
        return cityList.stream().map(CityDto::from).collect(Collectors.toList());
    }
}
