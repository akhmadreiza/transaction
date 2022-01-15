package com.training.springboot.transaction.repository;

import com.training.springboot.transaction.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
