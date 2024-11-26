package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country getById(Long id);
    Country getByName(String name);
//    List<Country> getAll();

//    void save(Country country);
//    void updateCountry(Country country);
//    void removeCountry(Long id);
}
