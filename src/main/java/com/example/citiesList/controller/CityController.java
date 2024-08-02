package com.example.citiesList.controller;

import com.example.citiesList.config.service.CityService;
import com.example.citiesList.model.City;
import com.example.citiesList.repository.CityRepository;
import com.example.citiesList.repository.InMemoryCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @GetMapping("/paginated") // eg. http://localhost:8080/paginated?page=1&size=1
    public List<City> getPaginatedCitiesList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return cityService.findPaginated(page, size);
    }
}
