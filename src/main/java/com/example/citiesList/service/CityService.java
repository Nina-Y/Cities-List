package com.example.citiesList.service;

import com.example.citiesList.model.City;
import com.example.citiesList.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public Page<City> getPaginatedCitiesList(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public void saveAll(List<City> cities) {
        cityRepository.saveAll(cities);
    }
}
