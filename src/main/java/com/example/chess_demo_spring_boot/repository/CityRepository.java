package com.example.chess_demo_spring_boot.repository;

import com.example.chess_demo_spring_boot.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(Long id);
    City getByName(String name);
}
