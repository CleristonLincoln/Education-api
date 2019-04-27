package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.adress.City;
import com.education.model.adress.Neighborhood;
import com.education.repository.query.NeighborhoodRepositoryQuery;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long>, NeighborhoodRepositoryQuery{

	List<Neighborhood> findByCity(City id);

}
