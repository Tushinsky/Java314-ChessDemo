package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.City;
import com.example.chess_demo_spring_boot.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{
//    public void setCityRepository(CityRepository cityRepository) {
//        this.cityRepository = cityRepository;
//    }

    private final CityRepository cityRepository;

    @Override
    @Transactional
    public Optional<City> getBy_Id(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    @Transactional
    public City getByName(String name) {
        return cityRepository.getByName(name);
    }

    @Override
    @Transactional
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    @Transactional
    public void updateCity(City city) {
        cityRepository.save(city);
    }

    @Override
    @Transactional
    public void removeCity(Long id) {
        cityRepository.deleteById(id);
    }
}
