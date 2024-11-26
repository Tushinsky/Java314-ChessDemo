package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.Country;
import com.example.chess_demo_spring_boot.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
//    public void setCountryRepository(CountryRepository countryRepository) {
//        this.countryRepository = countryRepository;
//    }

    private final CountryRepository countryRepository;

    @Override
    public Country getBy_Id(Long id) {
        return countryRepository.getById(id);
    }

    @Override
    public Country getByName(String name) {
        return countryRepository.getByName(name);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void removeCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
