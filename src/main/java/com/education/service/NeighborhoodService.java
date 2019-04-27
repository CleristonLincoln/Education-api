package com.education.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.adress.City;
import com.education.model.adress.Neighborhood;
import com.education.repository.CityRepository;
import com.education.repository.NeighborhoodRepository;
import com.education.service.exception.CityException;


@Service
public class NeighborhoodService {
	
	
	@Autowired private NeighborhoodRepository repository;
	@Autowired private CityRepository cityRepository;

	
	public Neighborhood saving(@Valid Neighborhood entity) {

		Optional<City> idCity = cityRepository.findById(entity.getCity().getId());
		
		if (!idCity.isPresent()) {
			throw new CityException();
		}
		return repository.save(entity);
	}

}
