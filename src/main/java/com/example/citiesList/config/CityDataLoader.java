package com.example.citiesList.config;

import com.example.citiesList.model.City;
import com.example.citiesList.repository.InMemoryCityRepository;
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

    private final InMemoryCityRepository inMemoryCityRepository;

    public CityDataLoader(InMemoryCityRepository inMemoryCityRepository) {
        this.inMemoryCityRepository = inMemoryCityRepository;
    }

   @Override
    public void afterPropertiesSet() {
        loadCityData();
    }

    private void loadCityData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(citiesFile.getInputStream(), StandardCharsets.UTF_8))) {
            List<City> cities = reader.lines()
                    .map(line -> line.split(","))
                    .filter(data -> data[0] != null)
                    .map(data -> new City(Long.valueOf(data[0].trim()), data[1].trim(), data[2].trim()))
                    .collect(Collectors.toList());

            inMemoryCityRepository.saveAll(cities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


