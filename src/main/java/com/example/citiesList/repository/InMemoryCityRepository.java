package com.example.citiesList.repository;

import com.example.citiesList.model.City;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryCityRepository implements CityRepository {

    private final List<City> cities = new ArrayList<>();

    @Override
    public List<City> findAll() {
        return new ArrayList<>(cities);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cities.stream()
                .filter(city -> city.getId().equals(id)).findFirst();
    }

    @Override
    public List<City> findPaginated(int page, int size) {
        int start = Math.min(page * size, cities.size());
        int end = Math.min((page + 1) * size, cities.size());
        return cities.subList(start, end);
    }

    @Override
    public void saveAll(List<City> cities) {
        this.cities.clear();
        this.cities.addAll(cities);
    }
}
