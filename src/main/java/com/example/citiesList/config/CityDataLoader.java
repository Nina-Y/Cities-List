package com.example.citiesList.config;

import com.example.citiesList.model.City;
import com.example.citiesList.repository.CityRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDataLoader implements InitializingBean {

    @Value("classpath:cities.csv")
    private Resource citiesFile;

    private final CityRepository cityRepository;

    public CityDataLoader(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

   @Override
    public void afterPropertiesSet() {
        loadCityData();
    }

    private void loadCityData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(citiesFile.getInputStream(), StandardCharsets.UTF_8))) {
            List<City> cities = reader.lines()
                    .map(line -> line.split(","))
                    .filter(data -> data.length >= 2)
                    .map(data -> new City(data[0].trim(), data[1].trim()))
                    .collect(Collectors.toList());

            cityRepository.saveAll(cities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


