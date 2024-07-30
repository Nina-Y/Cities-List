package com.example.citiesList.controller;

import com.example.citiesList.model.City;
import com.example.citiesList.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/viewAll")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @GetMapping("/page")
    public Page<City> getPaginatedCitiesList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cityService.getPaginatedCitiesList(pageable);
    }
}
