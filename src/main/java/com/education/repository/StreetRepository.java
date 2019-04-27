package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.adress.Neighborhood;
import com.education.model.adress.Street;
import com.education.repository.query.StreetRepositoryQuery;

public interface StreetRepository extends JpaRepository<Street, Long>, StreetRepositoryQuery{
	
	List<Street> findByNeighborhood(Neighborhood id);

	String findByCep(String streetCEP);

	
}
