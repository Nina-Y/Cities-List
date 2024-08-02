package com.example.citiesList.config.service;

import com.example.citiesList.model.City;
import com.example.citiesList.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {


    @Autowired
    CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }


    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }


    public List<City> findPaginated(int page, int size) {
        return cityRepository.findPaginated(page, size);
    }
}
