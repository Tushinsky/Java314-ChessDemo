package com.example.chess_demo_spring_boot.dto;

import com.example.chess_demo_spring_boot.domain.Country;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class CountryDto {
    Long id;
    String name;

    public static CountryDto from(Country country) {
        return new CountryDto(country.getId(), country.getName());
    }

    public static List<CountryDto> from(List<Country> countries) {
        return countries.stream().map(CountryDto::from).collect(Collectors.toList());
    }
}
