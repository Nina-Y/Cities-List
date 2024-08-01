package com.example.citiesList.repository;

import com.example.citiesList.model.City;
import java.util.List;
import java.util.Optional;

public interface CityRepository {
    List<City> findAll();
    Optional<City> findById(Long id);
    List<City> findPaginated(int page, int size);
    void saveAll(List<City> cities);
}
