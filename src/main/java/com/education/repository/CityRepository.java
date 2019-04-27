package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.adress.City;
import com.education.model.adress.State;
import com.education.repository.query.CityRepositoryQuery;

public interface CityRepository extends JpaRepository<City, Long>, CityRepositoryQuery{

	List<City> findByState(State id);

}
