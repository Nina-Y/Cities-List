package com.example.citiesList.controller;

import com.example.citiesList.model.City;
import com.example.citiesList.repository.CityRepository;
import com.example.citiesList.repository.InMemoryCityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CityController {

    private InMemoryCityRepository inMemoryCityRepository;

    public CityController(InMemoryCityRepository inMemoryCityRepository) {
        this.inMemoryCityRepository = inMemoryCityRepository;
    }

    @GetMapping("/viewAll")
    public List<City> getAllCities() {
        return inMemoryCityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        return inMemoryCityRepository.findById(id);
    }

    @GetMapping("/paginated") // eg. http://localhost:8080/paginated?page=1&size=1
    public List<City> getPaginatedCitiesList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return inMemoryCityRepository.findPaginated(page, size);
    }
}
