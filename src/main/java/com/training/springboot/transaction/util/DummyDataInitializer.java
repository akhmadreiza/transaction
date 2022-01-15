package com.training.springboot.transaction.util;

import com.training.springboot.transaction.entity.City;
import com.training.springboot.transaction.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DummyDataInitializer {

    @Autowired
    private CityRepository cityRepository;

    @PostConstruct
    public void initDummyDataCity() {
        //example using save all
        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setCode("JKT");
        city.setName("Jakarta");
        cities.add(city);

        city = new City();
        city.setCode("BDG");
        city.setName("Bandung");
        cities.add(city);

        city = new City();
        city.setCode("SMG");
        city.setName("Semarang");
        cities.add(city);

        city = new City();
        city.setCode("SBY");
        city.setName("Surabaya");
        cities.add(city);

        Long startSaveAll = System.currentTimeMillis();
        cityRepository.saveAll(cities);
        Long finishSaveAll = System.currentTimeMillis();
        log.info("[initDummyDataCity] saveAll address took: {}ms", finishSaveAll - startSaveAll);

        //example using save with iteration
        List<City> cities2 = new ArrayList<>();
        City city2 = new City();
        city2.setCode("CLG");
        city2.setName("Cilegon");
        cities2.add(city2);

        city2 = new City();
        city2.setCode("SRG");
        city2.setName("Serang");
        cities2.add(city2);

        city2 = new City();
        city2.setCode("TGS");
        city2.setName("Tangerang Selatan");
        cities2.add(city2);

        city2 = new City();
        city2.setCode("TGR");
        city2.setName("Tangerang");
        cities2.add(city2);

        Long startSave = System.currentTimeMillis();
        cityRepository.saveAll(cities2);
        Long finishSave = System.currentTimeMillis();
        log.info("[initDummyDataCity] save address took: {}ms", finishSave - startSave);

        List<City> currData = cityRepository.findAll();
        currData.forEach(city1 -> log.info(city1.getCode()));
    }
}
